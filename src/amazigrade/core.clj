(ns amazigrade.core
  (:require [amazigrade.data :as data]))


;; Defining some helper functions to get things going. These will be useful in determining a list of final grades.

(defn percentify
  "adjust raw score to percentile"
  [raw percentile]
  (* (/ raw 100) percentile))

(defn percentify-vector
  "maps a vector of percentile adjustments to a vector of grades"
  [weights grades]
  (map percentify grades weights))

(defn augment-vectors
  "augments all the elements in all of the vectors in a grades list into their corresponding weighted values"
  [grades]
  (mapv (partial percentify-vector weights) grades))

(defn round
  "As we are using Java data-structures, I am reaching for Java-interop here to wrap simple round function in Clojure."
  [number]
  (.setScale (bigdec number) 0 java.math.RoundingMode/HALF_EVEN))

(defn average
  "averages all the numbers in a sequence."
  [grades]
  (/ (reduce + grades) (count grades)))

(defn class-average [grades]
  (float (average grades)))


;; Sometimes we need to do a bellcurve. This next pair simply expands or contracts your grades around the class average,
;; depending on the value of coef.

(defn bellify
  "augemnts a single grade on a curve function. The size of the curve depends on the coefficient and the distance from the class-average."
  [coef grade grades]
  (+ grade (* coef (- grade (class-average grades)))))


(defn bell-curve
  "This function applies the curve to every grade in a sequence."
  [grades coef]
  (->> grades (map (partial #(bellify coef % grades)))))


;; Using "macro magic" we can bring much of the above into something very readable. Abstracting out final-grades is useful
;; if you don't yet know the final grades and need to calculate them for use with other functions.
;; If you already know the class' final grades you can easily place them in the data.clj file under data/class-finals
;; and call on them as needed for bell-curves and other simple transformations. Just make sure they are ordered correctly!

(def final-grades
(->> data/grades
     (map (partial percentify-vector data/weights))
     (mapv #(apply + %))))

;; Now we can easily associate the class numbers of the students with their corresponding grades in a map of key/value pairs, using zipmap.

(def nums&finals
(zipmap data/nums final-grades))



(comment
;; Let's see it in action! You can play with the coefficient values, then add or subtract as needed.
;; In this example, we have a coefficient of -0.5, which will contract the grade list around the average.

(map int (map round (bell-curve final-grades -0.5)))


;; Now we'll dock 5 points across the board.

(map #(+ -5 %) final-grades)


;; How about both?

(map int (map round (map #(+ -5 %) (bell-curve final-grades -0.5))))


;; Do this if you need to reorder the list to match the excel file. And don't forget to round the numbers and convert them to integers!

(map int (map round (map class-finals data/official-nums)))


;; What if you want to set the class average to 75 and position the rest of the grades around that average?

(map int (map round (map #(+ (- 75 (class-average final-grades)) %) final-grades)))


;; making it pretty with the thread-last macro and for fun we'll add in the bell-curve function too...

(->> (bell-curve final-grades 0.5)
     (map #(+ (- 75 (class-average final-grades)) %))
     (map round)
     (map int))

  )
