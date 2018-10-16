(ns MP2)

(def p1 '(and x (or x (and y (not z)))))                               ;; Test Seq 1
(def p2 '(and (and z false) (or x true false)))                        ;; Test Seq 2
(def p3 '(or true a))                                                  ;; Test Seq 3

;;TODO; Write And Function
(defn andExpression [expList]
  (cond
    (some false? expList) false                                        ;; Returns false immediately
    (some symbol? (rest expList))                                      ;; Checks for symbols
      (cond
        (> 2 (count (remove true? (rest expList))))
          (first (remove true? (rest expList)))                        ;; 0/1 symbols, return symbol minus Truth's
        (<= 2 (count (remove true? (rest expList))))
          (distinct (remove true? expList))                            ;; 2+ symbols, return list minus Truth's
        )
    )
  )

;;TODO: Write Or Function
(defn orExpression [expList]
  (cond
    (some true? expList) true                                           ;; Returns true immediately
    (some symbol? (rest expList))                                       ;; Checks for symbols
      (cond
        (> 2 (count (remove false? (rest expList))))
          (first (remove false? (rest expList)))                        ;; 0/1 symbol. return symbol minus False
        (<= 2 (count (remove false? (rest expList))))
          (distinct (remove false? expList))                            ;; 2+ symbol. return list minus False
        )
    :else nil
    )
  )

;;TODO: Write Not Function
(defn notExpression [expList]
  (cond
    (some true? expList) false                                          ;; Takes true out, replaces with false
    (some false? expList) true                                          ;; Takes false out, replaces with true.
    (some symbol? expList) expList                                      ;; Returns the expression if not simplified
    )
  )

;;TODO: Write Logic
(defn simplify [expList]
  (let [ expList (map (fn [i]
         (if (seq? i)
           (simplify i)
           i))
       expList)]
    expList
  (cond
    (== 0 (compare (first expList) 'and))
      (andExpression expList)                                             ;; Checks for and
    (== 0 (compare (first expList) 'or))
      (orExpression expList)                                              ;; Checks for or
    (== 0 (compare (first expList) 'not))
      (notExpression expList)                                             ;; Checks for not
    :ELSE true
   )
   )
  )

(defn deep-substitute [m l]
  (map (fn [i]
         (if (seq? i)
           (deep-substitute m i)
           (m i i)))
       l))

(defn evalexp [bindings expList]
  (simplify(deep-substitute bindings expList))
  )