(ns gr.normalize-test
  (:require [clojure.test :refer :all]
            [clj-time.format :as f]
            [gr.normalize :refer :all]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Helper Functions
(defn parse-date [date]
  (f/parse (f/formatter :date) date))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Public API
(defn normalized? [noramlized-row]
  (= noramlized-row
     {:first-name "Bob"
      :last-name "Baker"
      :gender :male
      :favorite-color "blue"
      :date-of-birth (parse-date "1980-10-10")}))

(defn comma-format-found? [result]
  (= result
     {:genders {"m" :male
                "f" :female}
      :parser-fn comma-delimited-parser}))

(deftest test-normalize-row
  (testing "Detects the correct format"
    (is (comma-format-found? (detect-format "Baker, Bob, m, blue, 1980-10-10"))))
  (testing "Returns the correct value for comma-seperated rows"
    (is (normalized? (normalize-row "Baker, Bob, m, blue, 1980-10-10")))))
