(ns gr.rest-server
  (:require [schema.core :as s]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :refer [run-jetty]]
            [clojure.data.json]
            [clj-time.format :as f]
            [gr.normalize :as n]
            [gr.views :as views]))



(def data (atom []))

;; TODO remove this
(s/set-fn-validation! true)

(s/defn render-response [response-data :- [n/NormalizedRecord]
                         sort-fn] :- s/Str
  (views/render-data-to-json (sort-fn response-data)))

(defroutes rest-handler
  (POST "/records" [last-name first-name gender favorite-color date-of-birth]
        (let [normalized-record (n/normalize-row (clojure.string/join "," [last-name
                                                                           first-name
                                                                           gender
                                                                           favorite-color
                                                                           date-of-birth]))]
          (swap! data conj normalized-record ))
        {:status 201})
  (GET "/records/gender" []
       (render-response @data views/by-gender))
  (GET "/records/birthdate" []
       (render-response @data views/by-date-of-birth))
  (GET "/records/name" []
       (render-response @data views/by-last-name)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Public API
(defn start [initial-data-set jetty-params]
  (reset! data initial-data-set)
  (run-jetty rest-handler jetty-params))
