# elings-boot
Things Suite Boot Server

## 초기 데이터 셋업 하기 
### Local Seed 데이터 파일로 부터 초기 데이터 셋업하기  
실행 방법 : application.properties를 아래와 같이 설정 한 후 서버 실행 
```shell
# 초기 데이터 셋업 플래그
elidom.initial.setup=true
# 기본 도메인 아이디 
elidom.initial.domain.id=1
# 기본 도메인 코드		
elidom.initial.domain.name=Things Suite
# 기본 도메인 이름
elidom.initial.domain.brand_name=Things Suite
# 기본 도메인 URL
elidom.initial.domain.url=suite.hatiolab.com
# 기본 도메인의 관리자 아이디
elidom.initial.admin.id=admin 
# 기본 도메인의 관리자 이름
elidom.initial.admin.name=admin 
# 기본 도메인의 관리자 이메일
elidom.initial.admin.email=admin@example.com
# 기본 도메인의 관리자 암호 
elidom.initial.admin.passwd=admin 
# 첨부파일 관리 루트 디렉토리
elidom.initial.storage.root=/storage
# Local파일 기반으로 seed 실행하겠다는 설정 (인터넷 연결이 안 될 경우 반드시 이 모드로 설정)
elidom.initial.seed.source=seed-file
```

### Seed Server로 부터 초기 데이터 셋업하기  
실행 방법 : application.properties를 아래와 같이 설정 한 후 서버 실행 
```shell
# 초기 데이터 셋업 플래그
elidom.initial.setup=true
# 기본 도메인 아이디					
elidom.initial.domain.id=1
# 기본 도메인 코드
elidom.initial.domain.name=Things Suite
# 기본 도메인 이름
elidom.initial.domain.brand_name=Things Suite
# 기본 도메인 URL
elidom.initial.domain.url=suite.hatiolab.com
# 기본 도메인의 관리자 아이디		
elidom.initial.admin.id=admin
# 기본 도메인의 관리자 이름
elidom.initial.admin.name=admin
# 기본 도메인의 관리자 이메일
elidom.initial.admin.email=admin@example.com
# 기본 도메인의 관리자 암호
elidom.initial.admin.passwd=admin
# 첨부파일 관리 루트 디렉토리
elidom.initial.storage.root=/storage
# Seed Server 기반으로 Seed를 실행하겠다는 설정						
elidom.initial.seed.source=seed-server	
# Seed Server Base URL
elidom.initial.seed.base_url=http://admin.hatiolab.com/rest
# Seed Server에 관리되는 SeedRepository ID
elidom.initial.seed.id=15
```

## 도메인 템플릿 셋업 하기
### Local Seed 데이터 파일로 부터 도메인 셋업하기
실행 방법 : Setting 화면에서 아래 설정 내용을 확인 한 후 없다면 수정한 후 Domain 관리 화면에서 [추가] 버튼을 클릭하여 도메인 관련 정보 입력 후 실행
```shell
# Local파일 기반으로 seed 실행하겠다는 설정 (인터넷 연결이 안 될 경우 반드시 이 모드로 설정)
seed.source = seed-file
```

### Seed Server로 부터 도메인 셋업하기 
실행 방법 : Setting 화면에서 아래 설정 내용을 확인 한 후 없다면 수정한 후 Domain 관리 화면에서 [추가] 버튼을 클릭하여 도메인 관련 정보 입력 후 실행
```shell
# Seed Server 기반으로 Seed를 실행하겠다는 설정
seed.source = seed-server
# Seed Server Base URL
seed.server.base.url = http://admin.hatiolab.com/rest
# Seed Server에 관리되는 SeedRepository ID
seed.export.repo.id = 15
```

## 도메인 데이터 Export 하기
### Local 파일로 도메인 데이터 Export하기
Local 파일이란 프로젝트의 src/main/resources/seeds 하위에 리소스 파일로 데이터를 export하겠다는 의미이므로 
이 때는 반드시 development 프로파일로 실행해야 하고 IDE(Eclipse)내 에서 실행해야 가능하다.
이런 환경이 아니라면 Seed Server로 Export하게 된다. 

실행 방법 : Setting 화면에서 아래 설정 내용을 확인 한 후 없다면 수정한 후 Domain 관리 화면에서 [내보내기] 버튼을 클릭하여 실행
```shell  
# Local파일 기반으로 seed 실행하겠다는 설정 (인터넷 연결이 안 될 경우 반드시 이 모드로 설정)
seed.source = seed-file
```

### Seed Server로 도메인 데이터 Export 하기 
설정된 Seed Server에서 관리하는 Repository ID로 도메인 데이터를 Export한다.

실행 방법 : Setting 화면에서 아래 설정 내용을 확인 한 후 없다면 수정한 후 Domain 관리 화면에서 [내보내기] 버튼을 클릭하여 실행
```shell  
# Seed Server 기반으로 Seed를 실행하겠다는 설정
seed.source = seed-server
# Seed Server Base URL
seed.server.base.url = http://admin.hatiolab.com/rest
# Seed Server에 관리되는 SeedRepository ID
seed.export.repo.id = 15
```
