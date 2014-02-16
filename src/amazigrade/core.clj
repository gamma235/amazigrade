(ns amazigrade.core
  (:require [amazigrade.data :as data] :reload))

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

(defn round [number]
  (.setScale (bigdec number) 0 java.math.RoundingMode/HALF_EVEN))


;; time to put these functions to good use

(def augmented-grades
  (augment-vectors data/grades))



(mapv #(apply + %) augmented-grades)


;; Using list comprehension and "macro magic" we can refactor this code into something much more consise!


(def final-grades
(->> data/grades (map (partial percentify-vector [10.0 20.0 15.0 25.0 30.0])) (mapv #(apply + %))))


;; Now we can associate the class numbers of the students with their corresponding grades in a map of key/value pairs, using zipmap.

(def class-finals
(zipmap data/nums final-grades))



;; Now we need to reorder the list to match the excel file sent by the university and round the numbers.

(map round (map class-finals data/official-nums))



