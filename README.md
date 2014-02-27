# amazigrade

A pseudo-library in Clojure designed to help teachers with their grading

## Usage

Simply clone this and work with the functions dynamically. Replace the values in the data.clj file with your own and use the functions in core.clj as needed.



In this example, we have a coefficient of -0.5, which will contract the grade list around the average.

    (map int (map round (bell-curve final-grades -0.5)))


Now we'll dock 5 points across the board.

    (map #(+ -5 %) final-grades)


How about both?

    (map int (map round (map #(+ -5 %) (bell-curve final-grades -0.5))))


Do this if you need to reorder the list to match the excel file. And don't forget to round the numbers and convert them to integers!

    (map int (map round (map nums&finals data/official-nums)))


What if you want to set the class average to 75 and position the rest of the grades around that average?

    (map int (map round (map #(+ (- 75 (class-average final-grades)) %) final-grades)))



In this example we are using predefined functions and bindings to transform the info from the data file, repackaging it into a form that is easily copy/paste-able into an excel file. We can make it pretty with the thread-last macro and for fun we'll add in the bell-curve function too. The value 0.7 is a coefficient that determines how drastically the data is transformed.

    (->> (bell-curve final-grades 0.7)
         (map #(+ (- 75 (class-average final-grades)) %))
         (map round)
         (map int))



Play and have fun!

## License

Copyright © 2014

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
