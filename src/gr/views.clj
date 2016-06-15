(ns gr.views
  (:require [schema.core :as s]
            [clj-time.format :as f]
            [clojure.data.json :as json]
            [gr.normalize :refer [NormalizedRecord]]))

;; TODO remove this
(s/set-fn-validation! true)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Internal Defenitions
(def display-format  (f/formatter  "M/d/yyyy"))

(s/defn render-row-to-string [row :- NormalizedRecord] :- s/Str
  (str (:last-name row) "\t"
       (:first-name row) "\t"
       (:gender row) "\t"
       (:favorite-color row) "\t"
       (f/unparse (f/formatter display-format) (:date-of-birth row)) "\n"))

(defn date-converter [map-key value]
  (if  (= map-key :date-of-birth)
    (f/unparse (f/formatter display-format) value)
    value))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Public API
(s/defn by-gender [data :- [NormalizedRecord]] :- [NormalizedRecord]
  (sort-by (juxt :gender :last-name) data))

(s/defn by-last-name [data :- [NormalizedRecord]] :- [NormalizedRecord]
  (reverse
    (sort-by :last-name data)))

(s/defn by-date-of-birth [data :- [NormalizedRecord]] :- [NormalizedRecord]
  (sort-by :date-of-birth data))


(s/defn render-data-to-string
  [data :- [NormalizedRecord]] :- s/Str
  (reduce (fn [rendered-string normalized-record]
            (str rendered-string
                 (render-row-to-string normalized-record)))
          ""
          data))

(s/defn render-data-to-json
  [data :- [NormalizedRecord]] :- s/Str
  (json/write-str data
                  :value-fn date-converter))
