# assigment 
`made by Do seonggon`

**프로그램 기술 스택**

`java11`, `Gradle`, `Mysql`, `JPA`, `Querydsl`, `docker`

<br>

**프로그램 구현**
- [ ] 입점을 원하는 회사는 신청서를 제출할 수 있습니다. => 신청서 제출 api
- [ ] 입점을 원하는 회사는 제출된 신청서를 확인 할 수 있습니다. => 신청서 조회 api
- [ ] 신청서는 승인이 완료된 신청서를 포함하여 사업자등록번호당 1개만 신청할 수 있습니다. => 사업자등록번호 pk로 설정
- [ ] 관리자는 신청된 신청서 목록을 확인 후 승인 할 수 있습니다. => 제출된 신청서 조회 api + 신청서 승인 api
- [ ] 신청서가 승인될 시에 신청서에 저장된 회사 담당자 메일로 메일 발송을 합니다.

<br>

**프로그램 실행 준비작업**

1) DB(Mysql) 연결 - docker-compose.yml 파일 실행: `docker-compose up`
2) api 시동 - project 내 test폴더 > http 폴더 안 `admin-api`, `client-api` http 파일 시동 

ex.
![image](https://user-images.githubusercontent.com/62453668/174442919-f7fc35db-a111-4997-a945-28c4e32ede83.png)
