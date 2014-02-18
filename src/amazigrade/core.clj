(ns amazigrade.core
  (:require [amazigrade.data :as data]))


;; Defining some helper functions to get things going. These will be useful in determining a list of final grades.

(defn percentify
  "adjust raw score to percentile"
  [raw percentile]
  (* (/ raw 100) percentile))

(defn percentify-vector
  "maps a vector of percentile adjustments to a vector of grades"
  [pvec gvec]
  (mapv percentify gvec pvec))

(defn augment-vectors
  "augments all the elements in all of the vectors in a grades list into their corresponding weighted values"
  [grades]
  (mapv (partial percentify-vector [10.0 20.0 15.0 25.0 30.0]) grades))

(defn round [number]
  (.setScale (bigdec number) 0 java.math.RoundingMode/HALF_EVEN))


;; time to put these functions to good use

(def augmented-grades
  (augment-vectors data/grades))

(mapv #(apply + %) augmented-grades)


;; Using "macro magic" we can refactor much of the above to be much more readable.
;; Abstracting out final-grades is useful if you don't already know the final grades and you need to calculate them.
;; If you already know the classes final grades you can easily place them in the data.clj file under data/class-finals
;; and call on them as needed for bell-curves and simple transformations. Just make sure they are ordered correctly.

(def final-grades
(->> data/grades
     (map (partial percentify-vector [10.0 20.0 15.0 25.0 30.0]))
     (mapv #(apply + %))))


;; Now we can associate the class numbers of the students with their corresponding grades in a map of key/value pairs, using zipmap.

(def class-finals
(zipmap data/nums final-grades))


;; Now we need to reorder the list to match the excel file sent by the university, round the numbers and convert them to integers.

(map int (map round (map class-finals data/official-nums)))


;; Sometimes we need to do a bellcurve. This requires a separate set of functions and bindings. We'll start with calculating averages.

(defn average
  "averages all the numbers in a sequence."
  [grades]
  (/ (reduce + grades) (count grades)))

;; Abstracting out a class-average binding is convenient, but be sure to change final-grades to data/class-finals, depending on your needs.

(def class-average
  (float (average final-grades)))


;; onto the bell-curve functions. This pair simply expands or contracts your grades around the class average.

(defn bellify
  "augemnts a single grade on a curve function. The size of the curve depends on the coefficient and the distance from the class-average."
  [coef grade]
  (+ grade (* coef (- grade class-average))))


(defn bell-curve
  "This function applies the curve to every grade in a sequence."
  [grades coef]
  (->> grades (map (partial bellify coef))))


;; Let's see it in action! You can play with the coefficient values add or subtract as needed. In this example, we have a coefficient of 0.5.

(map int (map round (bell-curve final-grades 0.5)))


;; Now we'll dock 5 points across the board.

(map #(+ -5 %) final-grades)


;; How about both?

(map int (map round (map #(+ -5 %) (bell-curve final-grades 0.5))))


;; What if you want to set the class average to 75 and position the rest of the grades around that average?

(map int (map round (map #(+ (- 75 class-average) %) final-grades)))


;; making it pretty with the thread-last macro and for fun we'll add in the bell-curve function too...

(->> (bell-curve final-grades 0.5)
     (map #(+ (- 75 class-average) %))
     (map round)
     (map int))

