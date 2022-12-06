(ns rr.core
  (:require [rr.tools :refer :all]
            [clojure.string :as str])
  )

(defn get-lines [file-path]
  (str/split (slurp file-path) #"\n")
  )

(defn sum
  "Small sum helper method"
  [seq]
  (reduce + seq))

(defn
  total-common-items-value
  "Gets total value of common items"
  [backpacks]
  (map
    #(sum
       (items-value (apply common-items (split-rucksack %))))
    backpacks
    ))

(defn -main
  [& args]
  (let [lines (get-lines (first args))]
    (println
      "Common items total value"
      (sum (total-common-items-value lines)))
    )
  )
