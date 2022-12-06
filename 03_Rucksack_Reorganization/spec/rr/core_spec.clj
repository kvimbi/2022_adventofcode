(ns rr.core-spec
  (:require [speclj.core :refer :all]
            [rr.tools :refer :all]))

(describe "03 Solution"
          (it "should split string in two parts"
              (let [compartments (split-rucksack "AABB")]
                (should= 2 (count compartments))
                (should= "AA" (first compartments))
                (should= "BB" (last compartments))))

          (it "should find one common item"
              (let [
                    items1 #{\a \b \c}
                    items2 #{\b \c \d}
                    items3 #{\c \e \f}
                    common (common-items items1 items2 items3)
                    ]
                (should= #{\c} common)))

          (it "should find no common item"
              (let [
                    items1 #{\a \b \c}
                    items2 #{\d \e \f}
                    items3 #{\g \h \i}
                    common (common-items items1 items2 items3)
                    ]
                (should= #{} common)))

          (it "should value items correctly"
              (should= '(1 5 26 27 31 52) (items-value '(\a \e \z \A \E \Z))))
          )

(run-specs)