(ns gr.core
  (:gen-class)
  (:require [schema.core :as s ]
            [clj-time.core :as t]
            [gr.normalize :as n]
            [gr.views :as v]))

;; TODO remove this
(s/set-fn-validation! true)

(s/defn load-data
  "Loads a data file from the given path into memory"
  [path :- s/Str] :- [s/Str]
  (clojure.string/split (slurp path) #"\n"))

(s/defn load-all-data [file-paths :- [s/Str]] :- [s/Str]
  (flatten (map load-data file-paths)))

(s/defn normalize-rows [rows :- [s/Str]] :-s n/NormalizedRecord
  (map n/normalize-row rows))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [all-lines (load-all-data args)]
    (println
      (v/render-data-to-string (normalize-rows all-lines)))))
