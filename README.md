# Simple ShortUrl Application 만들기

1. spring web mvc
2. spring data jpa 
3. h2 database
4. thymeleaf
1. 화면 설명: 화면은 간단하다. 텍스트를 넣을 수 있는 빈칸이 있고 shortUrl로 등록하기 버튼이 있다.
예를 들어,
빈칸에 http://www.naver.com 을 입력하고, 등록하기 버튼을 누르면 localhost:10010/shortUrl/nvr 과 같은 짧게 만들어진 url을 화면에 뿌려준다.
등록이 완료된다면, localhost:10010/shortUrl/nvr을 들어가면 -> redirect가 되어야하고, http://www.naver.com로 이동해야 한다.

요구되는 것은 결국
 1. 화면에서 데이터를 받아올 수 있는지(Controller 개발, Form 개발)
 2. 받아온 url 을 shortUrl로 만들어서 DB에 1대1 매핑으로 저장할 수 있는지(Service 개발 / 코딩 역량)
 3. shortUrl은 unique 값을 어떻게 보장할 것인지(알고리즘 역량) - unique값이란, 예를 들어 한번 사용한 shortUrl은 다시는 다른 Url을 위해 사용되면 안된다.


    참고 자료 : https://sergeswin.com/394/


인터넷에 찾아보면, shortUrl 과 관련한 간단한 설명이  많다. 찾아가면서 만들어 본다.
화면은 대충 만들어도 된다. 타임리프 실력을 파악하는 것이 아니기 때문이다.

해당 테스트를 통해,
Spring에 대한 전반적인 이해,
302 redirect 처리와 같은 Web 에 대한 이해
DB CRUD 및 알고리즘에 대한 이해를 

종합적으로 평가하여,
70점 이상 되지않을 경우, 바로 프로젝트를 진행하지 않고,
해당 shortUrl 프로젝트를 선 진행한다.


2주차 과제

1. Application/Json 통신으로 Rest 방식으로 바꿔보자.

2. Interceptor를 활용해서, shortUrl을 생성하려고 들어오는 LongUrl에

 "naver",  "daum", "nate", "google" 이 들어있다면,
 
shortUrl을 생성하지 않고, errorMessage를 client에게 보내보자.


#모던 자바
1주차 : Functional Interface
예습 철저히
함수형 인터페이스	Descripter	Method
Predicate	T -> boolean	boolean test(T t)
Consumer	T -> void	void accept(T t)
Supplier	() -> T	T get()
Function<T, R>	T -> R	R apply(T t)
Comparator	(T, T) -> int	int compare(T o1, T o2)
Runnable	() -> void	void run()
Callable	() -> T	V call()


