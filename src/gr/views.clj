(ns gr.views
  (:require [schema.core :as s]
            [gr.normalize :refer [NormalizedRecord]]))

;; TODO remove this
(s/set-fn-validation! true)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Public API
(s/defn by-gender [data :- [NormalizedRecord]] :- [NormalizedRecord]
  (sort-by (juxt :gender :last-name) data))

(s/defn by-last-name [data :- [NormalizedRecord]] :- [NormalizedRecord]
  (reverse
    (sort-by :last-name data)))

(s/defn by-date-of-birth [data :- [NormalizedRecord]] :- [NormalizedRecord]
  (sort-by :date-of-birth data))

(s/defn render-row-to-string [row :- NormalizedRecord] :- s/Str
  (str (:last-name row) "\t"
       (:first-name row) "\t"
       (:gender row) "\t"
       (:favorite-color row) "\t"
       (:date-of-birth row) "\n"))

(s/defn render-data-to-string
  [data :- [NormalizedRecord]] :- s/Str
  (reduce (fn [rendered-string normalized-record]
            (str rendered-string
                 (render-row-to-string normalized-record)))
          ""
          data))
