(ns cart.core-test
  (:require [clojure.test :refer :all]
            [cart.core :refer :all]))


(deftest counting-cart-items
  (testing "empty"
    (testing "has zero items"
      (is (= 0 0)))))

