삼쩜삼과제 정리 및 설명

1. 필수 사용 기술
  1-1. Java17, SpringBoot 3.x, JPA, H2, Gradle
2. 필수 세팅 사항 : UTF-8, application/json content-type지정, swagger:url-/3o3/swagger.html, h2 Embedded memMode, port:8080
3. 구현 API
  3-1. 회원가입
  3-2. 로그인
  3-3. 스크래핑
  3-4. 환급액계산
4. 서비스 기능
  4-1. 사용자가 서비스 가입 및 로그인 기능(Spring Security 사용)
  4-2. 가입자의 소득정보 조회(스크랩) (서비스내 DB에 저장)
  4-3. 결정세액 계산 후 반환
5. 회원가입 상세 기능
   -회원가입 규격 Request Param 요청 받아 회원가입
   -가입가능자 list에서 가입가능자인지 확인
   -이미 가입한 가입자인지 확인
   -가입등록
6. 로그인 상세 기능
   -ID 체크
   -PW 체크
   -JWT키 발행
7. 스크래핑 상세 기능
   -로그인 user체크(token검사)
   -스크랩API 전송(Header set)
   -국민연금 공제 저장(DeductionDetail table, kind:NAT)
   -신용카드 공제 저장(DeductionDetail table, kind:CDC)
8. 






