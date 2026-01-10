(ns paip.auxfns-test
  (:require [clojure.test :refer :all]
            [paip.auxfns :refer :all]))

(deftest test-mappend
  (is (= '(1 1 2 2 3 3) (mappend #(list % %) '(1 2 3)))))

(deftest test-starts-with
  (is (starts-with '(a b c) 'a))
  (is (not (starts-with '(a b c) 'b))))

(deftest test-partition-if
  (let [[yes no] (partition-if odd? '(1 2 3 4 5))]
    (is (= '(1 3 5) yes))
    (is (= '(2 4) no))))

(deftest test-pat-match
  (is (= no-bindings (pat-match 'a 'a)))
  (is (= fail (pat-match 'a 'b)))
  (is (= '{?x 1} (pat-match '?x 1)))
  (is (= '{?x 1 ?y 2} (pat-match '(?x ?y) '(1 2))))
  (is (= fail (pat-match '(?x ?x) '(1 2))))
  (is (= '{?x 1} (pat-match '(?x ?x) '(1 1))))
  (is (= '{?x (1 2)} (pat-match '?x '(1 2))))
  ;; Nested
  (is (= '{?x 2} (pat-match '(1 ?x 3) '(1 2 3)))))

(deftest test-find-anywhere
  (is (= 'a (find-anywhere 'a '(b (c a) d))))
  (is (nil? (find-anywhere 'e '(b (c a) d)))))

(deftest test-queue
  (let [q (make-queue)
        q1 (enqueue 1 q)
        q2 (enqueue 2 q1)]
    (is (not (empty-queue-p q1)))
    (is (= 1 (front q2)))
    (is (= 2 (front (dequeue q2))))
    (is (empty-queue-p (dequeue (dequeue q2))))))
