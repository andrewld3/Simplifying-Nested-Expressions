(ns MP2)

;;TODO; Write And Function
(defn andExpression [expList]
  (cond
    (some false? expList) false
    (some symbol? (rest expList))
      (cond
        (> 2 (count (remove true? (rest expList)))) (last (remove true? (rest expList)))
        (<= 2 (count (remove true? (rest expList)))) (remove true? expList)
        )
    )
  )

;;TODO: Write Or Function
(defn orExpression [expList]
  (cond
    (some true? expList) true
    (some symbol? (rest expList))
      (cond
        (<= 2 (count (remove false? (rest expList)))) (remove false? expList)
        (> 2 (count (remove false? (rest expList)))) (last (remove false? (rest expList)))
        )
    :else false
    )
  )

;;TODO: Write Not Function
(defn notExpression [explist]
  (cond
    (== (last expList) true) false
    (== (last expList) false) true
    ))

;;TODO: Write Logic

