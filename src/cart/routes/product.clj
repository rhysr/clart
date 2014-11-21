(ns cart.routes.product
  (:require [compojure.core :refer :all]
            [hiccup.element :only (link-to unordered-list)]
            [cart.views.layout :as layout]
            [cart.models.product :as product]))

(defn product-page [productId]
  (layout/common
    [:div {:class "jumbotron"}
     [:div {:class "container"}
      [:h1 (str "An amazing product - " productId) ]
      [:p "blah"]
      [:p [:button {:class "btn btn-primary btn-lg" :role "button" :href "#"} "Buy now"]]]]))

(defroutes product-routes
  (GET ["/product/:id", :id #"[0-9]+"] [id]
       (let [id (Integer. id)]
       (if (contains? product/productList id)
         (product-page id)
         (ring.util.response/not-found "Not Found") ))))
