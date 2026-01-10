(ns paip.intro-test
  (:require [clojure.test :refer :all]
            [paip.intro :refer :all]))

(deftest test-names
  (is (= 'Public (last-name '(John Q Public))))
  (is (= 'John (first-name '(John Q Public))))
  (is (= 'Grace (first-name '(Admiral Grace Murray Hopper))))
  (is (= 'Larry (first-name '(Sir Larry Olivier)))))

(deftest test-numbers-and-negations
  (is (= '(1 -1 2 -2) (numbers-and-negations '(1 a 2 b)))))

(deftest test-power
  (is (= 1 (power 2 0)))
  (is (= 8 (power 2 3)))
  (is (= 1024 (power 2 10))))

(deftest test-count-atoms
  (is (= 1 (count-atoms 'a)))
  (is (= 3 (count-atoms '(a (b) c))))
  (is (= 0 (count-atoms nil))))

(deftest test-count-anywhere
  (is (= 3 (count-anywhere 'a '(a (b a) (c (a))))))
  (is (= 0 (count-anywhere 'x '(a b c)))))

(deftest test-dot-product
  (is (= 32 (dot-product '(1 2 3) '(4 5 6))))) ;; 4+10+18 = 32
