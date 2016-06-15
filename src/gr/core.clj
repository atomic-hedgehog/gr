(ns gr.core
  (:gen-class)
  (:require [schema.core :as s ]
            [clj-time.core :as t]
            [gr.normalize :as n]
            [gr.rest-server :as rest-server]
            [gr.views :as v]))

;; TODO remove this
(s/set-fn-validation! true)

(s/defn load-data
  "Loads a data file from the given path into memory"
  [path :- s/Str] :- [s/Str]
  (clojure.string/split (slurp path) #"\n"))

(s/defn load-all-data [file-paths :- [s/Str]] :- [s/Str]
  (flatten (map load-data file-paths)))

(s/defn normalize-rows [rows :- [s/Str]] :- n/NormalizedRecord
  (map n/normalize-row rows))

(defn print-sorted-dataset [title sort-fn normalized-data]
    (println "------ " title "--------")
    (println
      (v/render-data-to-string (sort-fn normalized-data))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [all-lines (load-all-data args)
        normalized-data (normalize-rows all-lines)]
    (print-sorted-dataset "Gender Sorted" v/by-gender normalized-data)
    (print-sorted-dataset "Last Name Descending Sorted" v/by-last-name normalized-data)
    (print-sorted-dataset "DOB Sorted" v/by-date-of-birth normalized-data)
    (rest-server/start normalized-data {:port 3333})))
