(ns cart.test.handler
  (:use clojure.test
        ring.mock.request
        cart.handler))

(deftest test-app
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200))
      (is (not (= nil (re-find #"Hello World"(:body response)))))))
  (testing "product page"
    (let [response (app (request :get "/product/123456"))]
      (is (= (:status response) 200))
      (is (not (= nil (re-find #"Hello World"(:body response)))))))

  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404)))))
