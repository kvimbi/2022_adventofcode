(ns rr.tools
  (:require [clojure.set :refer [intersection]])
  )

(defn split-rucksack
  "Splits content of the rucksack into 2 compartments"
  [content]
  (let [
        length (count content)
        middle (/ length 2)
        ]
    (list
      (subs content 0 middle)
      (subs content middle)
      )
    )
  )

(defn common-items
  "Returns a set of common items in the compartments"
  [& compartments]
  (apply intersection (map #(set %) compartments))
  )

(defn items-value
  "Returns value in the compartment.
  a to z = 1 to 26
  A to Z = 27 to 52
  "
  [items]
  (map
    #(let [raw-value (int %)]
       (if (> raw-value 90)
         (- raw-value 96)
         (- raw-value 38)
         )
       )
    items)
  )

