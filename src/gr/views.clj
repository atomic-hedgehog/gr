(ns gr.views
  (:require [schema.core :as s]
            [gr.normalize :refer [NormalizedRecord]]))

;; TODO remove this
(s/set-fn-validation! true)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Public API
(s/defn by-gender [data :- [NormalizedRecord]]
  (sort-by (juxt :gender :last-name) data))

(s/defn by-last-name [data :- [NormalizedRecord]]
  (reverse
    (sort-by :last-name data)))

(s/defn by-date-of-birth [data :- [NormalizedRecord]]
  (sort-by :date-of-birth data))
