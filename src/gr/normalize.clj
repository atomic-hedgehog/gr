(ns gr.normalize
  (:require [schema.core :as s]
            [clj-time.core :as t]))

;; TODO remove this
(s/set-fn-validation! true)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Schemas
(def NormalizedRecord
  "Schema to represent the final form of a record after any necessary transformations"
  {:first-name s/Str
   :last-name s/Str
   :gender s/Keyword
   :favorite-color s/Str
   :date-of-birth s/Str})


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Record Format

(def RecordFormatSpecifier
  "All of the information required to parse a specific row format"
  {:genders {s/Str s/Keyword}
   :parser-fn s/Any})

;; comma delimited
(def ParsedRow
  "Ensures that the parsed row has the correct number of elements"
  [(s/one s/Str "lastName")
   (s/one s/Str "firstName")
   (s/one s/Str "gender")
   (s/one s/Str "favoriteColor")
   (s/one s/Str "dateOfBirth")])

(s/defn comma-delimited-parser
  "Parses a comma-delimited row into it's constituant parts"
  [row :- s/Str] :- ParsedRow
  (map clojure.string/trim
       (clojure.string/split row #",")))

(def comma-delimited-format
  {:genders {"m" :male
             "f" :female}
   :parser-fn comma-delimited-parser})

;; format detection
(def formats
  {"," comma-delimited-format})

(s/defn detect-format
  "Detects the separator format of a row. Assumptions:
  * The first element of the row is the last-name string.
  * The second element of the row is the first-name string
  * Whatever is between the first and second elements contains the delimiter"
  [row :- s/Str] :- RecordFormatSpecifier
  (->> row
      (re-find #"\w+(\W+)\w+")
      second
      clojure.string/trim
      formats))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Public API
(s/defn normalize-row
  "Accepts a data row as a string and returns the normalized result.
  The data row may be in any of the supported formats."
  [row :- s/Str] ;:- NormalizedRecord
  (let [formatter (detect-format row)
        parse (:parser-fn formatter)
        [last-name first-name gender favorite-color date-of-birth] (parse row)]
    {:last-name last-name
     :first-name first-name
     :gender (get-in formatter [:genders gender] :unknown)
     :favorite-color favorite-color
     :date-of-birth date-of-birth}))

