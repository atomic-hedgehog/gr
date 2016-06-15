(ns gr.rest-server-test
  (:require [clojure.test :refer :all]
            [clj-time.format :as f]
            [gr.rest-server :refer :all]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Public API
(defn request [method resource handler & params]
   (handler {:request-method method :uri resource :params (first params)}))

#_(deftest test-render-response
  )

(deftest test-routes
  (is (= 201 (:status (request :post "/records" rest-handler {:first-name "Bob"
                                                              :last-name "C"
                                                              :gender "male"
                                                              :favorite-color "green"
                                                              :date-of-birth "1990-03-01"}))))
  (is (= 200 (:status (request :get "/records/gender" rest-handler))))
  (is (= 200 (:status (request :get "/records/birthdate" rest-handler))))
  (is (= 200 (:status (request :get "/records/name" rest-handler)))))
