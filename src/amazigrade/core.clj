(ns amazigrade.core
  (:require [amazigrade.data :as data]))

;; Defining some helper functions to get things going.

(defn percentify
  "adjust raw score to percentile"
  [raw percentile]
  (* (/ raw 100) percentile))

(defn percentify-vector [pvec gvec]
  "maps a vector of percentile adjustments to a vector of grades"
  (mapv percentify gvec pvec))

(defn augment-vectors [grades]
  "augments all the elements in all of the vectors in a grades list into their corresponding weighted values"
  (mapv (partial percentify-vector [10.0 20.0 15.0 25.0 30.0]) grades))

;; time to put these functions to good use

(def class-1
  (augment-vectors data/grades-1))

(def class-2
  (augment-vectors data/grades-2))

(def class-4
  (augment-vectors data/grades-4))

(def class-5
  (augment-vectors data/grades-5))

(mapv #(apply + %) class-1)
(mapv #(apply + %) class-2)
(mapv #(apply + %) class-4)
(mapv #(apply + %) class-5)


;; Using list comprehension and "macro magic" we can refactor this code into something much more consise!


(def final-grades1
(->> data/grades-1 (map (partial percentify-vector [10.0 20.0 15.0 25.0 30.0])) (mapv #(apply + %))))

(def final-grades2
(->> data/grades-2 (map (partial percentify-vector [10.0 20.0 15.0 25.0 30.0])) (mapv #(apply + %))))

(def final-grades4
(->> data/grades-4 (map (partial percentify-vector [10.0 20.0 15.0 25.0 30.0])) (mapv #(apply + %))))

(def final-grades5
(->> data/grades-5 (map (partial percentify-vector [10.0 20.0 15.0 25.0 30.0])) (mapv #(apply + %))))

;; Now we can associate the class numbers of the students with their corresponding grades in a map of key/value pairs, using zipmap.

(def class1-finals
(zipmap data/class1-nums final-grades1))

(def class2-finals
(zipmap data/class1-nums final-grades2))

(def class4-finals
(zipmap data/class1-nums final-grades4))

(def class5-finals
(zipmap data/class1-nums final-grades5))

;; Now we need to reorder the list to match the excel file sent by Iwashita-san.

(map class1-finals data/iwashita1)
(map class2-finals data/iwashita2)
(map class4-finals data/iwashita4)
(map class5-finals data/iwashita5)


