(ns paip.auxfns
  (:refer-clojure :exclude [symbol]))

;; In Clojure, many of these auxiliary functions are either built-in
;; or can be implemented using standard library functions.

(defmacro once-only
  "Returns the code built by BODY.  If any of VARIABLES
  might have side effects, they are evaluated once and stored
  in temporary variables that are then passed to BODY."
  [variables & body]
  (let [temps (mapv (fn [_] (gensym)) variables)]
    `(let [~@(interleave temps variables)]
       ~@body)))

(defn funcall-if [fn arg]
  (if fn (fn arg) arg))

(defn rest2 [x]
  (rest (rest x)))

(defn find-anywhere [item tree]
  (cond
    (= item tree) tree
    (and (coll? tree) (seq tree)) (or (find-anywhere item (first tree))
                                      (find-anywhere item (rest tree)))
    :else nil))

(defn starts-with [list x]
  (and (sequential? list) (= (first list) x)))

(def find-all-if filter)

(defn find-all [item sequence & {:keys [test test-not] :or {test =}}]
  (if test-not
    (remove (fn [x] (test-not item x)) sequence)
    (filter (fn [x] (test item x)) sequence)))

(defn partition-if [pred list]
  [(filter pred list) (remove pred list)])

(defn maybe-add [op exps & [if-nil]]
  (cond
    (empty? exps) if-nil
    (= 1 (count exps)) (first exps)
    :else (cons op exps)))

(def seq-ref nth)

(defn symbol [& args]
  (clojure.core/symbol (apply str args)))

(defn new-symbol [& args]
  (gensym (apply str args)))

(defn last1 [list]
  (last list))

(def mappend mapcat)

(defn mklist [x]
  (if (list? x) x (list x)))

(def flatten clojure.core/flatten)

(defn random-elt [seq]
  (rand-nth seq))

(defn member-equal [item list]
  (drop-while #(not= item %) list))

(def compose comp)

;; Debugging Output Facility

(def ^:dynamic *dbg-ids* #{})

(defn dbg [id format-string & args]
  (when (*dbg-ids* id)
    (apply printf format-string args)
    (println)))

(defn debug [& ids]
  (alter-var-root #'*dbg-ids* into ids))

(defn undebug [& ids]
  (if (empty? ids)
    (alter-var-root #'*dbg-ids* empty)
    (alter-var-root #'*dbg-ids* #(apply disj % ids))))

(defn dbg-indent [id indent format-string & args]
  (when (*dbg-ids* id)
    (dotimes [_ indent] (print "  "))
    (apply printf format-string args)
    (println)))

;; Pattern Matching Facility

(def fail nil)
(def no-bindings {})

(declare variable-p match-variable binding-val get-binding extend-bindings)

(defn pat-match
  ([pattern input] (pat-match pattern input no-bindings))
  ([pattern input bindings]
   (cond
     (= bindings fail) fail
     (variable-p pattern) (match-variable pattern input bindings)
     (= pattern input) bindings
     (and (sequential? pattern) (sequential? input))
     (pat-match (rest pattern) (rest input)
                (pat-match (first pattern) (first input) bindings))
     :else fail)))

(defn variable-p [x]
  (and (symbol? x) (.startsWith (name x) "?")))

(defn get-binding [var bindings]
  (find bindings var))

(defn binding-val [binding]
  (val binding))

(defn match-variable [var input bindings]
  (let [binding (get-binding var bindings)]
    (cond
      binding (if (= input (binding-val binding)) bindings fail)
      :else (extend-bindings var input bindings))))

(defn extend-bindings [var val bindings]
  (assoc bindings var val))

(defn lookup [var bindings]
  (get bindings var))

;; Delayed computation
(def force clojure.core/force)

;; Queues
(defn make-queue []
  clojure.lang.PersistentQueue/EMPTY)

(defn enqueue [item q]
  (conj q item))

(defn dequeue [q]
  (pop q))

(defn front [q]
  (peek q))

(defn empty-queue-p [q]
  (empty? q))

(defn queue-nconc [q list]
  (into q list))

;; Other

(defn sort* [seq pred & {:keys [key] :or {key identity}}]
  (sort-by key pred seq))

(defn reuse-cons [x y x-y]
  (if (and (= x (first x-y)) (= y (rest x-y)))
      x-y
      (cons x y)))

(defn length=1 [x]
  (= 1 (count x)))

(defn rest3 [list]
  (drop 3 list))

(defn unique-find-if-anywhere [predicate tree & [found-so-far]]
  (let [found-so-far (or found-so-far #{})]
    (cond
      (coll? tree) (reduce (fn [acc x]
                             (unique-find-if-anywhere predicate x acc))
                           found-so-far
                           tree)
      (predicate tree) (conj found-so-far tree)
      :else found-so-far)))

(defn find-if-anywhere [predicate tree]
  (cond
    (coll? tree) (some #(find-if-anywhere predicate %) tree)
    (predicate tree) tree
    :else nil))

(defn not-null [x] (not (nil? x)))

(defn first-or-nil [x]
  (if (sequential? x) (first x) nil))

(defn first-or-self [x]
  (if (sequential? x) (first x) x))
