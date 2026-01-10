(ns paip.intro
  (:require [paip.auxfns :refer [mappend]]))

(defn last-name [name]
  "Select the last name from a name represented as a list."
  (last name))

(def ^:dynamic *titles*
  #{'Mr 'Mrs 'Miss 'Ms 'Sir 'Madam 'Dr 'Admiral 'Major 'General})

(defn first-name [name]
  "Select the first name from a name represented as a list."
  (if (*titles* (first name))
    (first-name (rest name))
    (first name)))

(def names '((John Q Public) (Malcolm X)
             (Admiral Grace Murray Hopper) (Spot)
             (Aristotle) (A A Milne) (Z Z Top)
             (Sir Larry Olivier) (Miss Scarlet)))

(defn number-and-negation [x]
  "If x is a number, return a list of x and -x."
  (if (number? x)
      (list x (- x))
      nil))

(defn numbers-and-negations [input]
  "Given a list, return only the numbers and their negations."
  (mappend number-and-negation input))

(defn atomprint [exp & [depth]]
  "Print each atom in exp, along with its depth of nesting."
  (let [depth (or depth 0)]
    (if (not (sequential? exp))
      (printf "ATOM: %s, DEPTH %d\n" exp depth)
      (doseq [element exp]
        (atomprint element (+ depth 1))))))

(defn power [x n]
  "Power raises x to the nth power.  N must be an integer >= 0.
   This executes in log n time, because of the check for even n."
  (cond (= n 0) 1
        (even? n) (let [p (power x (/ n 2))] (* p p))
        :else (* x (power x (- n 1)))))

(defn count-atoms [exp]
  "Return the total number of non-nil atoms in the expression."
  (cond (nil? exp) 0
        (not (sequential? exp)) 1
        (empty? exp) 0
        :else (+ (count-atoms (first exp))
                 (count-atoms (rest exp)))))

(defn count-all-atoms [exp & [if-null]]
  "Return the total number of atoms in the expression,
  counting nil as an atom only in non-tail position."
  (let [if-null (or if-null 1)]
    (cond (nil? exp) if-null
          (not (sequential? exp)) 1
          (empty? exp) 0
          :else (+ (count-all-atoms (first exp) 1)
                   (count-all-atoms (rest exp) 0)))))

(defn count-anywhere [item tree]
  "Count the times item appears anywhere within tree."
  (cond (= item tree) 1
        (not (sequential? tree)) 0
        (empty? tree) 0
        :else (+ (count-anywhere item (first tree))
                 (count-anywhere item (rest tree)))))

(defn dot-product [a b]
  "Compute the mathematical dot product of two vectors."
  (apply + (map * a b)))
