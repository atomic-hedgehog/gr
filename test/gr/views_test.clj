(ns gr.views-test
  (:require [clojure.test :refer :all]
            [clj-time.format :as f]
            [gr.views :refer :all]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Helper Functions
(defn parse-date [date]
  (f/parse (f/formatter :date) date))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Test Data
(def bob-b
  {:first-name "Bob"
   :last-name "B"
   :gender :female
   :favorite-color "blue"
   :date-of-birth (parse-date "1980-10-10")})

(def bob-c
  {:first-name "Bob"
   :last-name "C"
   :gender :male
   :favorite-color "blue"
   :date-of-birth (parse-date "1990-10-10")})

(def bob-d
   {:first-name "Bob"
    :last-name "D"
    :gender :female
    :favorite-color "blue"
    :date-of-birth (parse-date "1960-10-10")})

(def bob-e
  {:first-name "Bob"
   :last-name "E"
   :gender :male
   :favorite-color "blue"
   :date-of-birth (parse-date "1970-10-10")})

(def test-data
  [bob-c
   bob-d
   bob-b
   bob-e])

(def gender-sorted-test-data
  [bob-b
   bob-d
   bob-c
   bob-e])

(def last-name-sorted-test-data
  [bob-e
   bob-d
   bob-c
   bob-b])

(def dob-sorted-test-data
  [bob-d
   bob-e
   bob-b
   bob-c])


(def rendered-string "C\tBob\t:male\tblue\t10/10/1990\nD\tBob\t:female\tblue\t10/10/1960\nB\tBob\t:female\tblue\t10/10/1980\nE\tBob\t:male\tblue\t10/10/1970\n")

(def json-string "[{\"first-name\":\"Bob\",\"last-name\":\"C\",\"gender\":\"male\",\"favorite-color\":\"blue\",\"date-of-birth\":\"10\\/10\\/1990\"},{\"first-name\":\"Bob\",\"last-name\":\"D\",\"gender\":\"female\",\"favorite-color\":\"blue\",\"date-of-birth\":\"10\\/10\\/1960\"},{\"first-name\":\"Bob\",\"last-name\":\"B\",\"gender\":\"female\",\"favorite-color\":\"blue\",\"date-of-birth\":\"10\\/10\\/1980\"},{\"first-name\":\"Bob\",\"last-name\":\"E\",\"gender\":\"male\",\"favorite-color\":\"blue\",\"date-of-birth\":\"10\\/10\\/1970\"}]")
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Public API
(defn sorted-by-gender? [result]
  (= result
     gender-sorted-test-data))

(deftest test-sort-by-gender
  (testing "Sorts by gender and then last name"
    (is (sorted-by-gender? (by-gender test-data)))))

(defn sorted-by-last-name? [result]
  (= result
     last-name-sorted-test-data))

(deftest test-sort-by-last-anem
  (testing "Sorts by last name descending"
    (is (sorted-by-last-name? (by-last-name test-data)))))

(defn sorted-by-dob? [result]
  (= result
     dob-sorted-test-data))

(deftest test-sort-by-last-name
  (testing "Sorts by last name descending"
    (is (sorted-by-dob? (by-date-of-birth test-data)))))

(defn rendered-to-string? [result]
  (= result
     rendered-string))

(deftest test-sort-by-last-name
  (testing "Renders the data set to a string"
    (is (rendered-to-string? (render-data-to-string test-data)))))

(defn rendered-to-json? [result]
  (= result
     json-string))

(deftest test-sort-by-last-name
  (testing "Renders the data set to json"
    (is (rendered-to-json? (render-data-to-json test-data)))))
