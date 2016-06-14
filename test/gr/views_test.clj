(ns gr.views-test
  (:require [clojure.test :refer :all]
            [gr.views :refer :all]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Test Data
(def bob-b
  {:first-name "Bob"
   :last-name "B"
   :gender :female
   :favorite-color "blue"
   :date-of-birth "1980-10-10"})

(def bob-c
  {:first-name "Bob"
   :last-name "C"
   :gender :male
   :favorite-color "blue"
   :date-of-birth "1990-10-10"})

(def bob-d
   {:first-name "Bob"
    :last-name "D"
    :gender :female
    :favorite-color "blue"
    :date-of-birth "1960-10-10"})

(def bob-e
  {:first-name "Bob"
   :last-name "E"
   :gender :male
   :favorite-color "blue"
   :date-of-birth "1970-10-10"})

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


(def rendered-string "C\tBob\t:male\tblue\t1990-10-10\nD\tBob\t:female\tblue\t1960-10-10\nB\tBob\t:female\tblue\t1980-10-10\nE\tBob\t:male\tblue\t1970-10-10\n")
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
