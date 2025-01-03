# Condition
### DB
member 100명, order 100건, item 500개  
order에는 orderItem 랜덤 3개
### 상황 : 100건의 order조회
orders-simple은 item을 제외하고 order + member + delivery가 필요한 조회  
orders는 order + member + delivery + orderItem가 필요한 조회  
order(1) 기준 member(1), delivery(1), orderItem(N)
# ~ToOne
1. GET /orders-simple/non-fetch_join  
   쿼리 1(order)+100(member)+100(delivery)번  
   결과 : 283.29ms  
2. GET /orders-simple  
   쿼리 1번  
   결과 : 215.67ms  
   __23.8% 향상__  
# ~ToMany
1. GET /orders/non-fetch_join  
   쿼리 1(order)+100(member)+100(delivery)+100(order_item)+224(item)번  
   결과 : 401.96ms  
   참고 : ToOne관계 페치 조인만 하고 최적화는 하지 않았을 경우 : 384.64ms
2. GET /orders  
   쿼리 1+order item 2 + item 5 = 8  
   결과 : 269.49  
   참고 : default_batch_fetch_size=50  
   __32.9% 향상__

### GET /orders에서 쿼리가 왜 8번인가
1. default_batch_fetch_size=50이기 때문에 처음에 쿼리 1번으로 orderitem 50개 조회.
2. order당 item 3개 주문이므로, item 50 * 3 = 150개 필요함.
3. 쿼리 3번으로 50개씩 item 조회.
4. 나머지 order 50개 쿼리 1번으로 조회.
5. item이 랜덤 주문이어서 겹치는게 발생->쿼리 2개로 끝

### default_batch_fetch_size=100으로 세팅한다면?  
order 1번  
orderItem 1번  
item 3번  
총 1+1+3 = 5번

### 페이징(size=10)하는 경우 (default_batch_fetch_size=100)  
order 1번  
count 1번  
orderItem 1번  
item 1번  
응답시간 228.14ms