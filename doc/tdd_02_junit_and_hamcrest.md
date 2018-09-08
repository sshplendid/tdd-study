# 02 JUnit과 Hamcrest

## 체크리스트
  * 테스트 픽스처의 개념
  * JUnit 3
    * 단정문
    * 테스트 스위트
  * JUnit 4
    * ``@Test``
    * ``@BeforeClass``, ``@AfterClass``, ``@Before``, ``@After``
    * 예외처리 테스트
    * 시간 제한 테스트
    * ``@Runwith``
    * ``@SuiteClasses``
    * 고급 기능 소개
      * 파라미터화된 테스트
      * Rule
      * Theory
  * Hamcrest

## 2.1. JUnit

  * 에릭 감마와 켄트 벡에 의해 만들어진 Java 유닛 테스트 프레임워크
  * 꾸준히 버전업 진행중 (2018년 9월 기준 v5.3.0 릴리즈)
  * 특징
    * ``테스트 결과``가 ``예상``과 같은지 판별해주는 **단정문(Assertion)**
    * 여러 테스트에서 공용으로 사용할 수 있는 **테스트 픽스쳐(Test fixture)**
    * 테스트 작업을 수행할 수 있게 해주는 **테스트 러너(Test runner)**

### Test Fixture
  * 테스트를 반복적으로 수행하고 동일한 결과를 얻을 수 있게 하는 **일관된 테스트 환경** 혹은 **테스트 컨텍스트**
  * examples: ``setUp``, ``tearDown``, ...
  * 테스트 전/후로 테스트 환경을 구축하고 테스트 후 정리하는 작업을 함

## 2.1.1. JUnit 3

  * 규칙
     * ``TestCase``를 상속받음
     * 테스트 메소드의 이름은 반드시 ``test*``로 시작해야 함

```java
public class AccountTest extends TestCase {
  public void testGetBalance() {
    ...
  }
}
```

### JUnit 3의 구성요소
  * 테스트 픽스쳐 (Test fixture method)
    * ``setUp``: 각각의 메소드를 실행하기 전에 공통적으로 호출하는 메소드
    * ``tearDown``: 테스트 실행 후에 호출하는 메소드. 자원의 정리 등을 담당
  * 단정문 (Assertions) 
    * ``assertEquals([message], expected, actual)`` => 두 인자의 값 비교
      * ``double``타입의 경우 ``assertEquals([message], expected, actual, delta)``, delta는 오차보정값
    * ``assertSame([message], expected, actual)`` => 두 인자의 주소값(``==``) 비교
    * ``assertTrue([message], expected)`` => 논리 비교
    * ``assertNull([message], expected)`` => Null 체크
    * ``fail([message])`` => 무조건 테스트가 실패함
  * 테스트 러너 (Test runner)
    * IDE 독립적으로 JUnit을 사용할 수 있는 실행클래스
  * 테스트 스위트 (Test suite)
    * 여러 테스트를 한꺼번에 수행하고자 할때 사용
    * 테스트 스위트는 테스트 케이스와 테스트 스위트를 포함시킬 수 있다.
    * 메소드는 반드시 ``public static Test suit()``이어야 한다.
    * 테스트 추가는 ``suite.addTestSuite(테스트클래스.class);``형식으로 추가한다.
    * IDE, gradle이나 maven같은 빌드 자동화도구를 사용한다면 사용할 일이 있을지 모르겠다.

### 그 외에

#### JUnit 테스트 클래스는 테스트 메소드를 실행할 때마다 테스트 클래스를 인스턴스화한다.
각 테스트 케이스를 독립적으로 수행하기 위해서 ``Reflection`` API를 사용해서 클래스를 초기화한 후에 실행한다. 

## 2.1.2. JUnit 4

### 특징
  * Java 5 Annotation 지원
  * 메소드 명 제약 해소 => ``@Test``
  * 유연한 픽스쳐 => ``@Before`` ``@After`` ``@BeforeClass`` ``@AfterClass``
  * 예외 테스트 => ``@Test(expected = Exception.class)``
  * 시간제한 테스트 => ``@Test(timeout=1000)``
  * 테스트 무시 => ``@Ignore("This method isn't working yet")``
  * 배열 지원 => ``assertArrayEquals([message], expected, actual);``
  * TestRunner의 명시적 지정
    * ``@RunWith(JUnit4SpringRunner.class)``
    * 테스트 클래스를 실행하기 위한 러너를 명시적으로 지정
    * ``@RunWith``는 ``junit.runner.Runner``를 구현한 외부 클래스를 인자로 가짐
  * ``@SuiteClasses(Class[])``
    * 여러개의 테스트 클래스를 수행하기 위해 사용
    * ``@RunWith``를 사용해서 ``Suite.class``를 러너로 사용
  * 파라미터를 이용한 테스트