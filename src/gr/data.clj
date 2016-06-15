(ns gr.data
  (:require [schema.core :as s]
            [gr.normalize :refer [NormalizedRecord]]))

;; TODO remove this
(s/set-fn-validation! true)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Public API
(s/defn connect []
  (atom []))

(s/defn save-record [connection record]
  (swap! conj record))

(s/defn select-all-records [connection] :- [NormalizedRecord]
  @connection)
