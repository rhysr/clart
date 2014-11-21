(ns cart.routes.product
  (:require [compojure.core :refer :all]
            [hiccup.element :only (link-to unordered-list)]
            [cart.views.layout :as layout]))



(def
  productList
  [{:productId 12345 :name "Product 12345"}
   {:productId 23145 :name "Product 23145"}
   {:productId 12453 :name "Product 12453"}
   {:productId 24153 :name "Product 24153"}
   {:productId 13425 :name "Product 13425"}])

(defn product-page [productId]
  (layout/common
    [:h1 (str "An amazing product - " productId) ]))

(defroutes product-routes
  (GET ["/product/:id", :id #"[0-9]+"] [id] (product-page id)))
