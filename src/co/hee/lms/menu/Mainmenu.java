package co.hee.lms.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.hee.lms.course.service.CourseService;
import co.hee.lms.course.serviceImpl.CourseServiceImpl;
import co.hee.lms.course.vo.CourseVO;
import co.hee.lms.dept.service.DeptService;
import co.hee.lms.dept.serviceImpl.DeptServiceImpl;
import co.hee.lms.dept.vo.DeptVO;
import co.hee.lms.enrolment.service.EnrolmentService;
import co.hee.lms.enrolment.serviceImpl.EnrolmentServiceImpl;
import co.hee.lms.enrolment.vo.EnrolmentVO;
import co.hee.lms.professor.service.ProfessorService;
import co.hee.lms.professor.serviceImpl.ProfessorServiceImpl;
import co.hee.lms.professor.vo.ProfessorVO;
import co.hee.lms.student.service.StudentService;
import co.hee.lms.student.serviceImpl.StudentServiceImpl;
import co.hee.lms.student.vo.StudentVO;

public class Mainmenu {

		private StudentService stuS = new StudentServiceImpl();
		private ProfessorService proS = new ProfessorServiceImpl();
		private DeptService deptS = new DeptServiceImpl();
		private CourseService courS = new CourseServiceImpl();
		private EnrolmentService enS = new EnrolmentServiceImpl();
		private StudentVO svo= new StudentVO();
		private ProfessorVO pvo= new ProfessorVO();
		private DeptVO dvo= new DeptVO();
		private CourseVO cvo = new CourseVO();
		private EnrolmentVO evo= new EnrolmentVO();
		
		private Scanner sc = new Scanner(System.in);

		
		private void mainTitle() {
			System.out.println("======= L M S =======");
			System.out.println("   1. 학생 로그인");
			System.out.println("   2. 교수 로그인");
			System.out.println("   3.   종 료");
			System.out.println("=====================");
		}
		
		private void studentTitle() {
			System.out.println("======= 학  생 =======");
			System.out.println("   1. 학 생 정 보");
			System.out.println("   2.   과  목");
			System.out.println("   3. 수 강 신 청");
			System.out.println("   4.   학  점");
			System.out.println("   5.   학  과");
			System.out.println("   6. 돌 아 가 기");
			System.out.println("=====================");
		}
		
		
		private void professorTitle() {
			System.out.println("======= 교  수 =======");
			System.out.println("   1. 교 수 정 보");
			System.out.println("   2. 학 생 조 회");
			System.out.println("   3.   과  목");
			System.out.println("   4.   학  과");
			System.out.println("   5. 학 생 학 점");
			System.out.println("   6. 돌 아 가 기");
			System.out.println("=====================");
			
		}
		
		public void mainMenu() {
			boolean isTrue = false;
			int n;
			do {
				mainTitle();
				int pick = sc.nextInt();
				switch (pick) {
				case 1:
					svo = new StudentVO();
					System.out.println("> 아이디를 입력해주세요.");
					svo.setStudentId(sc.next());
					System.out.println("> 패스워드를 입력해주세요.");
					svo.setPassword(sc.next());
					svo = stuS.login(svo);
					if(svo.getStudentName() != null) {
						System.out.println(">> "+svo.getStudentName()+"님 접속합니다.");
						n = 1;
					} else {
						System.out.println("> 아이디 또는 패스워드가 일치하지 않습니다.");
						n = 0;
					}
					if(n != 0) {
						studentMain();
					}
					break;
				case 2:
					pvo = new ProfessorVO();
					System.out.println("> 아이디를 입력해주세요.");
					pvo.setProfessorId(sc.next());
					System.out.println("> 패스워드를 입력해주세요.");
					pvo.setPassword(sc.next());
					pvo = proS.login(pvo);
					if(pvo.getProfessorName() != null) {
						System.out.println(">> "+pvo.getProfessorName()+"님 접속합니다.");
						n = 1;
					} else {
						System.out.println("> 아이디 또는 패스워드가 일치하지 않습니다.");
						n = 0;
					}
					if(n != 0) {
						professorMain();
					}
					break;
				case 3:
					isTrue = true;
					System.out.println("> 종료합니다.");
					sc.close();
					break;
				default:
					System.out.println("> 해당 항목이 없습니다.");
					System.out.println("> 재입력해주세요.");
					break;
				}
			} while(!isTrue);
		}
		
		// 학생 메뉴
		private void studentMain() {
			boolean isTrue = false;
			do {
				studentTitle();
				int pick = sc.nextInt();
				switch(pick) {
				case 1:
					studentTable();
					break;
				case 2:
					courseTable();
					break;
				case 3:
					sugangTable();
//					System.out.println(">> 수강신청 기간이 아닙니다!\n");
					break;
				case 4:
					enrolmentTable();
					break;
				case 5:
					deptTableForStudent();
				case 6:
					isTrue = true;
					System.out.println("> 로그아웃 되었습니다.");
					break;
				default:
					System.out.println("> 해당 항목이 없습니다.");
					System.out.println("> 재입력해주세요.");
					break;
				}
			} while(!isTrue);
		}
		
		private void studentSubTitle() {
			System.out.println("======= 학 생 정 보 =======");
			System.out.println("   1. 전 화 번 호 변 경");
			System.out.println("   2. 패 스 워 드 변 경");
			System.out.println("   3.     탈  퇴");
			System.out.println("   4.   뒤 로 가 기");
			System.out.println("=========================");
		}
		private void studentSubTitleForProfessor() {
			System.out.println("======= 학 생 정 보 =======");
			System.out.println("   1.   전 체 학 생");
			System.out.println("   2.   학 생 조 회");
			System.out.println("   3.   학 생 등 록");
			System.out.println("   4.   뒤 로 가 기");
			System.out.println("=========================");
		}
		
		private void courseSubTitle() {
			System.out.println("======= 과      목 =======");
			System.out.println("   1.   전 체 과 목");
			System.out.println("   2.   수 강 과 목");
			System.out.println("   3.   과 목 조 회");
			System.out.println("   4.   뒤 로 가 기");
			System.out.println("=========================");
		}
		
		private void sugangSubTitle() {
			System.out.println("======= 수 강 신 청 =======");
			System.out.println("   1.   수 강 신 청");
			System.out.println("   2.   수 강 포 기");
			System.out.println("   3.   뒤 로 가 기");
			System.out.println("=========================");
		}

		private void courseSubTitleForProfessor() {
			System.out.println("======= 과       목 =======");
			System.out.println("   1.   전 체 과 목");
			System.out.println("   2.   강 의 과 목");
			System.out.println("   3.   과 목 조 회");
			System.out.println("   4.   과 목 등 록");
			System.out.println("   5. 수 강 인 원 변 경");
			System.out.println("   6.   강 좌 삭 제");
			System.out.println("   7.   뒤 로 가 기");
			System.out.println("=========================");
		}
		
		private void enrolmentSubTitle() {
			System.out.println("========== 성  적 ==========");
			System.out.println("   1.   전 체 성 적 조 회");
			System.out.println("   2.     성 적 조 회");
			System.out.println("   3.     뒤 로 가 기");
			System.out.println("===========================");
		}

		private void enrolmentSubTitleForProfessor() {
			System.out.println("======= 성  적 =======");
			System.out.println("   1.   전 체 성 적");
			System.out.println("   2.   성 적 조 회");
			System.out.println("   3.   성 적 등 록");
			System.out.println("   4.   성 적 변 경");
			System.out.println("   5.   성 적 삭 제");
			System.out.println("   6.   뒤 로 가 기");
			System.out.println("=====================");
		}
		
		private void deptSubTitleForStudent() {
			System.out.println("======= 학 과 정 보 =======");
			System.out.println("   1.   전 체 학 과");
			System.out.println("   2.   학 과 조 회");
			System.out.println("   3.   뒤 로 가 기");
			System.out.println("=========================");
		}
		
		private void deptSubTitle() {
			System.out.println("======= 학 과 정 보 =======");
			System.out.println("   1.   전 체 학 과");
			System.out.println("   2.   학 과 조 회");
			System.out.println("   3.   학 과 등 록");
			System.out.println("   4.  학 과 명 변 경");
			System.out.println("   5.   학 과 삭 제");
			System.out.println("   6.   뒤 로 가 기");
			System.out.println("=========================");
		}
		
		private void professorSubTitle() {
			System.out.println("======= 교 수 정 보 =======");
			System.out.println("   1.   전 체 교 수");
			System.out.println("   2.   교 수 조 회");
			System.out.println("   3.   교 수 등 록");
			System.out.println("   4. 패 스 워 드 변 경");
			System.out.println("   5.     탈  퇴");
			System.out.println("   6.   뒤 로 가 기");
			System.out.println("=========================");
		}
		
		// 학생 정보
		private void studentTable() {
			boolean isTrue = false;
			do {
				studentSubTitle();
				int pick = sc.nextInt();
				switch (pick) {
				case 1:
					studentUpdate();
					break;
				case 2:
					studentPasswordUpdate();
					break;
				case 3:
					studentDelete();
					break;
				case 4:
					isTrue = true;
					System.out.println("> 이전 항목으로 돌아갑니다.");
					break;
				default:
					System.out.println("> 해당 항목이 존재하지 않습니다.");
					System.out.println("> 재입력해주세요.");
					break;
				}
			} while(!isTrue);
		}
		
		private void studentSelectAll() {
			List<StudentVO> list = new ArrayList<StudentVO>();
			list = stuS.studentSelectAll();
			System.out.println("===== 전 체 학 생 =====");
			for (StudentVO svo : list) {
				svo.toString();
			}
			System.out.println("=====================");
			stuS.countStudent(svo);
		}

		private void studentSelect() {
			System.out.println("> 조회할 아이디를 입력해주세요.");
			svo.setStudentId(sc.next());
			svo = stuS.studentSelect(svo);
			if(stuS.selectInit(svo) == 1) {
				System.out.println("=======   학   생   =======");
				svo.toString();
				System.out.println("=========================\n");
			} else {
				System.out.println("> 해당 학번이 존재하지 않습니다.");
			}
		}

		private void studentInsert() {
			int n;
			System.out.println("> 등록할 아이디를 입력해주세요.");
			String id = sc.next();
			while(true) {
				int dbN = stuS.idCheck(id);
				if (dbN == 1) {
					System.out.println("> 중복된 학번입니다.");
					System.out.println("> 학번을 재입력해주세요.");
					id = sc.next();
				} else {
					System.out.println("> 사용할 수 있는 학번입니다.");
					break;
				}
			}
			svo.setStudentId(id);
			System.out.println("> 등록할 이름을 입력해주세요.");
			svo.setStudentName(sc.next());
			System.out.println("> 등록할 학과코드를 입력해주세요.");
			svo.setDeptCode(sc.next());
			System.out.println("> 등록할 전화번호를 입력해주세요.");
			System.out.println("> 예 : 010-xxxx-xxxx");
			svo.setTel(sc.next());
			System.out.println("> 등록할 패스워드를 입력해주세요.");
			svo.setPassword(sc.next());
			n = stuS.studentInsert(svo);
			if(n != 0) {
				System.out.println("> 학생 등록 완료");
			} else {
				System.out.println("> 학생 등록 실패");
			}
		}

		private void studentUpdate() {
			int n;
			System.out.println("> 변경할 전화번호를 입력해주세요.");
			System.out.println("> 예 : 010-xxxx-xxxx");
			svo.setTel(sc.next());
			System.out.println("> 학번을 입력해주세요.");
			String stuId = sc.next();
			while(true) {
				int dbId = stuS.idCheck(stuId);
				if (dbId == 0) {
					System.out.println("> 학번을 재입력해주세요.");
					stuId = sc.next();
				} else {
					System.out.println("> 해당 학번의 인증이 필요합니다.");
					break;
				}
			}
				svo.setStudentId(stuId);
				System.out.println("> 패스워드를 입력해주세요.");
				String pwd = sc.next();
				svo.setPassword(pwd);
					
			while(true) {
				int pw = stuS.passwordCheck(svo);
				if(pw == 0) {
					System.out.println("> 패스워드를 재입력해주세요");
					pwd = sc.next();
					svo.setPassword(pwd);
				} else {
					System.out.println("변경합니다.");
					break;
				}
			}
			
			n = stuS.studentUpdate(svo);			
			if ( n != 0 ) {
				System.out.println("> 수정 완료");
			} else {
				System.out.println("> 수정 실패");
			}
			
		}
		
		private void studentPasswordUpdate() {
			int n;
			System.out.println("> 학번을 입력해주세요.");
			String stuId = sc.next();
			while(true) {
				int dbId = stuS.idCheck(stuId);
				if (dbId == 0) {
					System.out.println("> 학번을 재입력해주세요.");
					stuId = sc.next();
				} else {
					System.out.println("> 해당 학번의 인증이 필요합니다.");
					break;
				}
			}
			svo.setStudentId(stuId);
			System.out.println("> 패스워드를 입력해주세요.");
			String pwd = sc.next();
			svo.setPassword(pwd);
			
			while(true) {
				int pw = stuS.passwordCheck(svo);
				if(pw == 0) {
					System.out.println("> 패스워드를 재입력해주세요");
					pwd = sc.next();
					svo.setPassword(pwd);
				} else {
					System.out.println("> 변경할 패스워드를 입력해주세요.");
					break;
				}
			}
			
			svo.setPassword(sc.next());
			n = stuS.studentPasswordUpdate(svo);			
			if ( n != 0 ) {
				System.out.println("> 수정 완료");
			} else {
				System.out.println("> 수정 실패");
			}
			
		}

		private void studentDelete() {
			int n;
			System.out.println("> 삭제할 학번을 입력해주세요.");
			String stuId = sc.next();
			while(true) {
				int dbId = stuS.idCheck(stuId);
				if (dbId == 0) {
					System.out.println("> 학번을 재입력해주세요.");
					stuId = sc.next();
				} else {
					System.out.println("> 해당 학번의 인증이 필요합니다.");
					break;
				}
			}
				svo.setStudentId(stuId);
				System.out.println("> 패스워드를 입력해주세요.");
				String pwd = sc.next();
				svo.setPassword(pwd);
					
			while(true) {
				int pw = stuS.passwordCheck(svo);
				if(pw == 0) {
					System.out.println("> 패스워드를 재입력해주세요");
					pwd = sc.next();
					svo.setPassword(pwd);
				} else {
					System.out.println("탈퇴합니다.");
					break;
				}
			}
			
			n = stuS.studentDelete(svo);
			if ( n!=0 ) {
				System.out.println("> 탈퇴 완료");
			} else {
				System.out.println("> 탈퇴 실패");
			}
		}
		
		
		// 과목
		private void courseTable() {
			boolean isTrue = false;
			do {
				courseSubTitle();
				int pick = sc.nextInt();
				switch (pick) {
				case 1:
					courseSelectAll();
					break;
				case 2:
					sugangSelectAll();
					break;
				case 3:
					courseSelect();
					break;
				case 4:
					isTrue = true;
					System.out.println("> 이전 항목으로 돌아갑니다.");
					break;
				default:
					System.out.println("> 해당 항목이 존재하지 않습니다.");
					System.out.println("> 재입력해주세요.");
					break;
				}
			} while(!isTrue);
		}


		private void courseSelectAll() {
			List<CourseVO> list2 = new ArrayList<CourseVO>();
			list2 = courS.courseSelectAll();
			System.out.println("====== 전 체 과 목 ======");
			for (CourseVO cvo : list2) {
				cvo.toString();
			}
			System.out.println("=======================");
			courS.countCourse(cvo);
		}

		private void sugangSelectAll() {
			List<EnrolmentVO> list3 = new ArrayList<EnrolmentVO>();
			System.out.println("> 학번을 입력하세요.");
			String stuId = sc.next();
			while(true) {
				int dbId = stuS.idCheck(stuId);
				if (dbId == 0) {
					System.out.println("> 해당 학번이 존재하지않습니다.");
					System.out.println("> 재입력해주세요.");
					stuId = sc.next();
				} else {
					System.out.println("> 해당 학번의 인증이 필요합니다.");
					System.out.println("> 패스워드를 입력해주세요.");
					break;
				}
			}
			evo.setStudentId(stuId);
			
			String pwd = sc.next();
			svo.setPassword(pwd);
			while(true) {
				int pw = stuS.passwordCheck(svo);
				if(pw == 0) {
					System.out.println("> 패스워드를 재입력해주세요");
					pwd = sc.next();
					svo.setPassword(pwd);
				} else {
					System.out.println(">> 인증되었습니다.\n");
					break;
				}
			}
			
			list3 = enS.enrolmentSelectStudentAll(evo);
			System.out.println("======== 수 강 과 목 ========");
			for (EnrolmentVO evo : list3) {
				evo.toString2();
			}
			System.out.println("==========================\n");
		}

		private void teachSelectAll() {
			List<CourseVO> list3 = new ArrayList<CourseVO>();
			System.out.println("> 교번을 입력하세요.");
			String proId = sc.next();
			while(true) {
				int dbId = proS.idCheck(proId);
				if (dbId == 0) {
					System.out.println("> 해당 교번이 존재하지않습니다.");
					System.out.println("> 재입력해주세요.");
					proId = sc.next();
				} else {
					break;
				}
			}
			cvo.setProfessorId(proId);
			list3 = courS.courseProfessorSelectAll(cvo);
			System.out.println("======== 강 의 과 목 ========");
			for (CourseVO cvo : list3) {
				cvo.toString();
			}
			System.out.println("==========================\n");
		}
		
		private void courseSelect() {
			System.out.println("> 조회할 과목코드를 입력해주세요.");
			cvo.setCourseId(sc.next());
			cvo = courS.courseSelect(cvo);
			if(courS.selectInit(cvo) == 1) {
				System.out.println("=======   과   목   =======");
				cvo.toString();
				System.out.println("=========================\n");
			} else {
				System.out.println("> 해당 과목이 존재하지 않습니다.");
			}
		}

		private void courseInsert() {
			int n;
			System.out.println("> 과목 등록을 시작합니다.");
			System.out.println("> 강좌번호를 입력해주세요.");
			cvo.setCourseId(sc.next());
			System.out.println("> 수강학년을 입력해주세요.");
			cvo.setGrade(sc.next());
			System.out.println("> 과목을 입력해주세요.");
			cvo.setCourseName(sc.next());
			System.out.println("> 학과코드를 입력해주세요.");
			cvo.setDeptCode(sc.next());
			System.out.println("> 교번을 입력해주세요.");
			cvo.setProfessorId(sc.next());
			System.out.println("> 수강인원을 입력해주세요.");
			cvo.setParticipants(sc.nextInt());
			n = courS.courseInsert(cvo);
			if(n != 0) {
				System.out.println("> 과목 등록 완료");
			} else {
				System.out.println("> 과목 등록 실패");
			}
		}

		private void courseUpdate() {
			int n;
			System.out.println("> 변경할 수강인원을 입력해주세요.");
			cvo.setParticipants(sc.nextInt());
			System.out.println("> 과목코드를 입력해주세요.");
			cvo.setCourseId(sc.next());
			n = courS.courseUpdate(cvo);
			if ( n != 0 ) {
				System.out.println("> 인원 수정 완료");
			} else {
				System.out.println("> 인원 수정 실패");
			}
		}

		private void courseDelete() {
			int n;
			System.out.println("> 삭제할 강좌번호를 입력해주세요.");
			cvo.setCourseId(sc.next());
			
			System.out.println("> 교번이 필요합니다.");
			String proId = sc.next();
			while(true) {
				int dbId = proS.idCheck(proId);
				if (dbId == 0) {
					System.out.println("> 교번을 재입력해주세요.");
					proId = sc.next();
				} else {
					System.out.println("> 해당 교번의 인증이 필요합니다.");
					break;
				}
			}
				pvo.setProfessorId(proId);
				System.out.println("> 패스워드를 입력해주세요.");
				String pwd = sc.next();
				pvo.setPassword(pwd);
					
			while(true) {
				int pw = proS.passwordCheck(pvo);
				if(pw == 0) {
					System.out.println("> 패스워드를 재입력해주세요");
					pwd = sc.next();
					pvo.setPassword(pwd);
				} else {
					System.out.println("> 강좌를 삭제합니다.");
					break;
				}
			}
			
			n = courS.courseDelete(cvo);
			if ( n!=0 ) {
				System.out.println("> 과목 삭제 완료");
			} else {
				System.out.println("> 과목 삭제 실패");
			}
		}
		
		// 수강
		private void sugangTable() {
			boolean isTrue = false;
			do {
				sugangSubTitle();
				int pick = sc.nextInt();
				switch (pick) {
				case 1:
					sugangInsert();
					break;
				case 2:
					sugangDelete();
					break;
				case 3:
					isTrue = true;
					System.out.println("> 이전 항목으로 돌아갑니다.");
					break;
				default:
					System.out.println("> 해당 항목이 존재하지 않습니다.");
					System.out.println("> 재입력해주세요.");
					break;
				}
			} while(!isTrue);
		}
		
		private void sugangInsert() {
			int n;
			System.out.println("> 수강 신청을 시작합니다.");
			System.out.println("> 학번을 입력해주세요.");
			String stuId = sc.next();
			while(true) {
				int dbId = stuS.idCheck(stuId);
				if (dbId == 0) {
					System.out.println("> 해당 학번이 존재하지 않습니다.");
					System.out.println("> 학번을 재입력해주세요.");
					stuId = sc.next();
				} else {
					System.out.println("> 해당 학번의 인증이 필요합니다.");
					break;
				}
			}
			evo.setStudentId(stuId);
			System.out.println("> 패스워드를 입력해주세요.");
			String pwd = sc.next();
			svo.setPassword(pwd);
			while(true) {
				int pw = stuS.passwordCheck(svo);
				if(pw == 0) {
					System.out.println("> 패스워드를 재입력해주세요");
					pwd = sc.next();
					svo.setPassword(pwd);
				} else {
					System.out.println(">> 인증되었습니다.\n");
					break;
				}
			}
			System.out.println("> 이름을 불러옵니다.");
			svo.setStudentId(stuId);
			String name = stuS.studentSelect(svo).getStudentName();
			evo.setStudentName(name);
			System.out.println("> 과목코드를 입력해주세요.");
			String courseId = sc.next();
			while(true) {
				int dbCourId = courS.courseCheck(courseId);
				if (dbCourId == 0) {
					System.out.println("> 해당 과목이 존재하지 않습니다.");
					System.out.println("> 과목코드를 재입력해주세요.");
					courseId = sc.next();
				} else {
					break;
				}
			}
			evo.setCourseId(courseId);
			System.out.println("> 과목을 불러옵니다.");
			cvo.setCourseId(courseId);
			String course = courS.courseSelect(cvo).getCourseName();
			evo.setCourseName(course);
			String proId = courS.courseSelect(cvo).getProfessorId();
			evo.setProfessorId(proId);
			pvo.setProfessorId(proId);
			String proName = proS.professorSelect(pvo).getProfessorName();
			evo.setProfessorName(proName);
			
			n = enS.enrolmentInsert(evo);
			if(n != 0) {
				System.out.println("> 수강신청 완료");
			} else {
				System.out.println("> 수강신청 실패");
			}
		}

		private void sugangDelete() {
			int n;
			System.out.println("> 학번을 입력해주세요.");
			String stuId = sc.next();
			while(true) {
				int dbId = stuS.idCheck(stuId);
				if (dbId == 0) {
					System.out.println("> 해당 학번이 존재하지않습니다.");
					System.out.println("> 재입력해주세요.");
					stuId = sc.next();
				} else {
					System.out.println("> 해당 학번의 인증이 필요합니다.");
					break;
				}
			}
			evo.setStudentId(stuId);
			System.out.println("> 패스워드를 입력해주세요.");
			String pwd = sc.next();
			svo.setPassword(pwd);
			while(true) {
				int pw = stuS.passwordCheck(svo);
				if(pw == 0) {
					System.out.println("> 패스워드를 재입력해주세요");
					pwd = sc.next();
					svo.setPassword(pwd);
				} else {
					System.out.println(">> 인증되었습니다.\n");
					break;
				}
			}
			System.out.println("> 수강포기할 과목코드를 입력해주세요.");
			courseIdselect();
			evo.setCourseId(sc.next());
			n = enS.enrolmentDelete(evo);
			if ( n!=0 ) {
				System.out.println("> 수강 포기 완료");
			} else {
				System.out.println("> 수강 포기 실패");
			}
		}

		// 학점
		private void enrolmentTable() {
			boolean isTrue = false;
			do {
				enrolmentSubTitle();
				int pick = sc.nextInt();
				switch (pick) {
				case 1:
					enrolmentStudentSelectAll();
					break;
				case 2:
					enrolmentSelectForStudent();
					break;
				case 3:
					isTrue = true;
					System.out.println("> 이전 항목으로 돌아갑니다.");
					break;
				default:
					System.out.println("> 해당 항목이 존재하지 않습니다.");
					System.out.println("> 재입력해주세요.");
					break;
				}
			} while(!isTrue);
		}

		private void enrolmentStudentSelectAll() {
			List<EnrolmentVO> list3 = new ArrayList<EnrolmentVO>();
			System.out.println("> 학번을 입력하세요.");
			String stuId = sc.next();
			while(true) {
				int dbId = stuS.idCheck(stuId);
				if (dbId == 0) {
					System.out.println("> 해당 학번이 존재하지않습니다.");
					System.out.println("> 재입력해주세요.");
					stuId = sc.next();
				} else {
					System.out.println("> 해당 학번의 인증이 필요합니다.");
					break;
				}
			}
			evo.setStudentId(stuId);
			System.out.println("> 패스워드를 입력해주세요.");
			String pwd = sc.next();
			svo.setPassword(pwd);
			while(true) {
				int pw = stuS.passwordCheck(svo);
				if(pw == 0) {
					System.out.println("> 패스워드를 재입력해주세요");
					pwd = sc.next();
					svo.setPassword(pwd);
				} else {
					System.out.println(">> 인증되었습니다.\n");
					break;
				}
			}
			
			list3 = enS.enrolmentSelectStudentAll(evo);
			System.out.println("===== 전 체 학 점 =====");
			for (EnrolmentVO evo : list3) {
				evo.toString();
			}
			System.out.println("=====================\n");
		}

		private void enrolmentSelectAll() {
			List<EnrolmentVO> list3 = new ArrayList<EnrolmentVO>();
			list3 = enS.enrolmentSelectAll();
			System.out.println("===== 전 체 학 생 =====");
			for (EnrolmentVO evo : list3) {
				evo.toString();
			}
			System.out.println("=====================\n");
		}

		private void enrolmentSelectForStudent() {
			System.out.println("> 성적 조회를 시작합니다.");
			System.out.println("> 학번을 입력해주세요.");
			String stuId = sc.next();
			while(true) {
				int dbId = stuS.idCheck(stuId);
				if (dbId == 0) {
					System.out.println("> 해당 학번이 존재하지 않습니다.");
					System.out.println("> 학번을 재입력해주세요.");
					stuId = sc.next();
				} else {
					System.out.println("> 인증이 필요합니다.");
					System.out.println("> 패스워드를 입력하세요.");
					break;
				}
			}
			
			evo.setStudentId(stuId);
			String pwd = sc.next();
			svo.setPassword(pwd);
			while(true) {
				int dbPwd = stuS.passwordCheck(svo);
				if (dbPwd == 0) {
					System.out.println("> 패스워드를 재입력해주세요.");
					pwd = sc.next();
					svo.setPassword(pwd);
				} else {
					System.out.println("> 인증되었습니다.");
					break;
				}
			}
			
			System.out.println("> 과목코드를 입력해주세요.");
			String courseId = sc.next();
			while(true) {
				int course = enS.courseCheck(courseId);
				if (course == 0) {
					System.out.println("> 해당 과목이 존재하지 않습니다.");
					System.out.println("> 과목코드를 재입력해주세요.");
					courseId = sc.next();
				} else {
					break;
				}
			}
			evo.setCourseId(courseId);
			evo = enS.enrolmentSelect(evo);
			if(enS.selectInit(evo) == 1) {
				System.out.println("=======   성   적   =======");
				evo.toString();
				System.out.println("==========================\n");
			} else {
				System.out.println("> 해당 결과가 존재하지 않습니다.");
			}
		}
		
		private void enrolmentSelect() {
			System.out.println("> 성적 조회를 시작합니다.");
			System.out.println("> 학번을 입력해주세요.");
			String stuId = sc.next();
			while(true) {
				int dbId = stuS.idCheck(stuId);
				if (dbId == 0) {
					System.out.println("> 해당 학번이 존재하지 않습니다.");
					System.out.println("> 학번을 재입력해주세요.");
					stuId = sc.next();
				} else {
					break;
				}
			}
			evo.setStudentId(stuId);
			
			System.out.println("> 과목코드를 입력해주세요.");
			String courseId = sc.next();
			while(true) {
				int course = enS.courseCheck(courseId);
				if (course == 0) {
					System.out.println("> 해당 과목이 존재하지 않습니다.");
					System.out.println("> 과목코드를 재입력해주세요.");
					courseId = sc.next();
				} else {
					break;
				}
			}
			evo.setCourseId(courseId);
			evo = enS.enrolmentSelect(evo);
			if(enS.selectInit(evo) == 1) {
				System.out.println("=======   성   적   =======");
				evo.toString();
				System.out.println("==========================\n");
			} else {
				System.out.println("> 해당 결과가 존재하지 않습니다.");
			}
		}
		

		private void enrolmentInsert() {
			int n;
			System.out.println("> 성적 등록을 시작합니다.");
			System.out.println("> 학번을 입력해주세요.");
			String stuId = sc.next();
			while(true) {
				int dbId = stuS.idCheck(stuId);
				if (dbId == 0) {
					System.out.println("> 해당 학번이 존재하지 않습니다.");
					System.out.println("> 학번을 재입력해주세요.");
					stuId = sc.next();
				} else {
					break;
				}
			}
			evo.setStudentId(stuId);
			System.out.println("> 학생이름을 불러옵니다.");
			svo.setStudentId(stuId);
			String name = stuS.studentSelect(svo).getStudentName();
			evo.setStudentName(name);
			System.out.println("> 과목코드를 입력해주세요.");
			String courseId = sc.next();
			while(true) {
				int dbCourId = courS.courseCheck(courseId);
				if (dbCourId == 0) {
					System.out.println("> 해당 과목이 존재하지 않습니다.");
					System.out.println("> 과목코드를 재입력해주세요.");
					courseId = sc.next();
				} else {
					break;
				}
			}
			evo.setCourseId(courseId);
			System.out.println("> 과목을 불러옵니다.");
			cvo.setCourseId(courseId);
			String course = courS.courseSelect(cvo).getCourseName();
			evo.setCourseName(course);
			System.out.println("> 강의교수 교번을 불러옵니다.");
			String proId = courS.courseSelect(cvo).getProfessorId();
			evo.setProfessorId(proId);
			System.out.println("> 교수이름을 불러옵니다.");
			pvo.setProfessorId(proId);
			String proName = proS.professorSelect(pvo).getProfessorName();
			evo.setProfessorName(proName);
			System.out.println("> 점수를 입력해주세요.");
			int score = sc.nextInt();
			while(true) {
				if (score < 0 || score > 100) {
					System.out.println("> 유효하지 않은 값입니다.");
					System.out.println("> 점수를 재입력해주세요.");
					score = sc.nextInt();
				} else {
					break;
				}
			}
			evo.setCourseScore(score);
			n = enS.enrolmentInsert(evo);
			if(n != 0) {
				System.out.println("> 성적 등록 완료");
				scoreCheck();
			} else {
				System.out.println("> 성적 등록 실패");
			}
		}
		
		private void scoreCheck() { // 학점 변환
		int n = 0;
		System.out.println("> 학점 자동 변환을 수행합니다.");
		String grade = enS.scoreCheck(evo);
		evo.setCourseGrade(grade);
		System.out.println("> 학생의 학번을 입력해주세요.");
		String stuId = sc.next();
		evo.setStudentId(stuId);
		System.out.println("> 과목코드를 입력해주세요.");
		evo.setCourseId(sc.next());
		n = enS.gradeUpdate(evo);
			if (n != 0) {
				System.out.println("> 학점 변환 완료");
			} else {
				System.out.println("> 학점 변환 실패");
			}
		}

		private void enrolmentUpdate() {
			int n;
			System.out.println("> 변경할 점수를 입력해주세요.");
			evo.setCourseScore(sc.nextInt());
			System.out.println("> 해당하는 학생의 학번을 입력하세요.");
			String stuId = sc.next();
			while(true) {
				int dbId = stuS.idCheck(stuId);
				if (dbId == 0) {
					System.out.println("> 해당 학번이 존재하지 않습니다.");
					System.out.println("> 학번을 재입력해주세요.");
					stuId = sc.next();
				} else {
					break;
				}
			}
			evo.setStudentId(stuId);
			System.out.println("> 과목코드를 입력하세요.");
			evo.setCourseId(sc.next());
			n = enS.enrolmentUpdate(evo);
			if ( n != 0 ) {
				System.out.println("> 성적 수정 완료");
			} else {
				System.out.println("> 성적 수정 실패");
			}
		}

		private void enrolmentDelete() {
			int n;
			System.out.println("> 삭제할 성적의 학번을 입력해주세요.");
			evo.setStudentId(sc.next());
			System.out.println("> 삭제할 성적의 과목코드를 입력해주세요.");
			courseIdselect();
			evo.setCourseId(sc.next());
			n = enS.enrolmentDelete(evo);
			if ( n!=0 ) {
				System.out.println("> 성적 삭제 완료");
			} else {
				System.out.println("> 성적 삭제 실패");
			}
		}
		
		private void courseIdselect() {
		List<CourseVO> list6 = new ArrayList<CourseVO>();
		list6 = courS.courseIdSelectAll();
		System.out.println("===== 과 목 코 드 =====");
		for (CourseVO cvo : list6) {
			cvo.toString2();
		}
		System.out.println("=====================");
		}
		
		// 학과
		private void deptTableForStudent() {
			boolean isTrue = false;
			do {
				deptSubTitleForStudent();
				int pick = sc.nextInt();
				switch (pick) {
				case 1:
					deptSelectAll();
					break;
				case 2:
					deptSelect();
					break;
				case 3:
					isTrue = true;
					System.out.println("> 이전 항목으로 돌아갑니다.");
					break;
				default:
					System.out.println("> 해당 항목이 존재하지 않습니다.");
					System.out.println("> 재입력해주세요.");
					break;
				}
			} while(!isTrue);
		}

		// 교수 메뉴
		private void professorMain() {
			boolean isTrue = false;
			do {
				professorTitle();
				int pick = sc.nextInt();
				switch (pick) {
				case 1:
					professorTable();
					break;
				case 2:
					studentTableForProfessor();
					break;
				case 3:
					courseTableForProfessor();
					break;
				case 4:
					deptTable();
					break;
				case 5:
					enrolmentTableForProfessor();
					break;
				case 6:
					isTrue = true;
					System.out.println("> 로그아웃 되었습니다.");
					break;
				default:
					System.out.println("> 해당 항목이 없습니다.");
					System.out.println("> 재입력해주세요.");
					break;
				}
		
			} while(!isTrue);
		}
		
		// 교수 정보
		private void professorTable() {
			boolean isTrue = false;
			do {
				professorSubTitle();
				int pick = sc.nextInt();
				switch (pick) {
				case 1:
					professorSelectAll();
					break;
				case 2:
					professorSelect();
					break;
				case 3:
					professorInsert();
					break;
				case 4:
					professorUpdate();
					break;
				case 5:
					professorDelete();
					break;
				case 6:
					isTrue = true;
					System.out.println("> 이전 항목으로 돌아갑니다.");
					break;
				default:
					System.out.println("> 해당 항목이 존재하지 않습니다.");
					System.out.println("> 재입력해주세요.");
					break;
				}
			} while(!isTrue);
			
		}
		
		private void professorSelectAll() {
			List<ProfessorVO> list4 = new ArrayList<ProfessorVO>();
			list4 = proS.professorSelectAll();
			System.out.println("===== 전 체 교 수 =====");
			for (ProfessorVO pvo : list4) {
				pvo.toString();
			}
			System.out.println("=====================");
			proS.countProfessor(pvo);
		}

		private void professorSelect() {
			System.out.println("> 교수 조회를 시작합니다.");
			System.out.println("> 교번을 입력해주세요.");
			pvo.setProfessorId(sc.next());
			pvo = proS.professorSelect(pvo);
			if(proS.selectInit(pvo) == 1) {
				System.out.println("=======   과   목   =======");
				pvo.toString();
				System.out.println("=========================\n");
			} else {
				System.out.println("> 해당 교번이 존재하지 않습니다.");
			}
			
		}

		private void professorInsert() {
			int n;
			System.out.println("> 교수 등록을 시작합니다.");
			System.out.println("> 교번을 입력해주세요.");
			pvo.setProfessorId(sc.next());
			System.out.println("> 교수이름을 입력해주세요.");
			pvo.setProfessorName(sc.next());
			System.out.println("> 과목코드를 입력해주세요.");
			pvo.setDeptCode(sc.next());
			System.out.println("> 패스워드를 입력해주세요.");
			pvo.setPassword(sc.next());
			n = proS.professorInsert(pvo);
			if(n != 0) {
				System.out.println("> 교수 등록 완료");
			} else {
				System.out.println("> 교수 등록 실패");
			}
		}

		private void professorUpdate() {
			int n;
			System.out.println("> 교번을 입력하세요.");
			String proId = sc.next();
			while(true) {
				int dbId = proS.idCheck(proId);
				if (dbId == 0) {
					System.out.println("> 교번을 재입력해주세요.");
					proId = sc.next();
				} else {
					System.out.println("> 해당 교번의 인증이 필요합니다.");
					break;
				}
			}
				pvo.setProfessorId(proId);
				System.out.println("> 패스워드를 입력해주세요.");
				String pwd = sc.next();
				pvo.setPassword(pwd);
					
			while(true) {
				int pw = proS.passwordCheck(pvo);
				if(pw == 0) {
					System.out.println("> 패스워드를 재입력해주세요");
					pwd = sc.next();
					pvo.setPassword(pwd);
				} else {
					System.out.println("> 변경할 패스워드를 입력하세요.");
					pvo.setPassword(sc.next());
					System.out.println("> 패스워드를 변경합니다.");
					break;
				}
			}
			
			n = proS.professorUpdate(pvo);
			if ( n != 0 ) {
				System.out.println("> 패스워드 수정 완료");
			} else {
				System.out.println("> 패스워드 수정 실패");
			}
		}

		private void professorDelete() {
			int n;
			System.out.println("> 탈퇴할 교번을 입력해주세요.");
			String proId = sc.next();
			while(true) {
				int dbId = proS.idCheck(proId);
				if (dbId == 0) {
					System.out.println("> 교번을 재입력해주세요.");
					proId = sc.next();
				} else {
					break;
				}
			}
			pvo.setProfessorId(proId);

			System.out.println("> 패스워드 인증이 필요합니다.");
			System.out.println("> 패스워드를 입력하세요.");
			String pwd = sc.next();
			pvo.setPassword(pwd);
			while(true) {
				int pw = proS.passwordCheck(pvo);
				if(pw == 0) {
					System.out.println("> 패스워드를 재입력해주세요.");
					pwd = sc.next();
					pvo.setPassword(pwd);
				} else {
					System.out.println("> 탈퇴합니다.");
					break;
				}
			}
			
			n = proS.professorDelete(pvo);
			if ( n!=0 ) {
				System.out.println("> 탈퇴 완료");
			} else {
				System.out.println("> 탈퇴 실패");
			}
		}
		
		// 학생 관리
		private void studentTableForProfessor() {
			boolean isTrue = false;
			do {
				studentSubTitleForProfessor();
				int pick = sc.nextInt();
				switch (pick) {
				case 1:
					studentSelectAll();
					break;
				case 2:
					studentSelect();
					break;
				case 3:
					studentInsert();
					break;
				case 4:
					isTrue = true;
					System.out.println("> 이전 항목으로 돌아갑니다.");
					break;
				default:
					System.out.println("> 해당 항목이 존재하지 않습니다.");
					System.out.println("> 재입력해주세요.");
					break;
				}
			} while(!isTrue);
		}
		
		// 과목 관리
		private void courseTableForProfessor() {
			boolean isTrue = false;
			do {
				courseSubTitleForProfessor();
				int pick = sc.nextInt();
				switch (pick) {
				case 1:
					courseSelectAll();
					break;
				case 2:
					teachSelectAll();
					break;
				case 3:
					courseSelect();
					break;
				case 4:
					courseInsert();
					break;
				case 5:
					courseUpdate();
					break;
				case 6:
					courseDelete();
					break;
				case 7:
					isTrue = true;
					System.out.println("> 이전 항목으로 돌아갑니다.");
					break;
				default:
					System.out.println("> 해당 항목이 존재하지 않습니다.");
					System.out.println("> 재입력해주세요.");
					break;
				}
			} while(!isTrue);
		}
		
		// 학점 관리
		private void enrolmentTableForProfessor() {
			boolean isTrue = false;
			do {
				enrolmentSubTitleForProfessor();
				int pick = sc.nextInt();
				switch (pick) {
				case 1:
					enrolmentSelectAll();
					break;
				case 2:
					enrolmentSelect();
					break;
				case 3:
					enrolmentInsert();
					break;
				case 4:
					enrolmentUpdate();
					scoreCheck();
					break;
				case 5:
					enrolmentDelete();
					break;
				case 6:
					isTrue = true;
					System.out.println("> 이전 항목으로 돌아갑니다.");
					break;
				default:
					System.out.println("> 해당 항목이 존재하지 않습니다.");
					System.out.println("> 재입력해주세요.");
					break;
				}
			} while(!isTrue);
		}
		
		// 학과 정보
		private void deptTable() {
			boolean isTrue = false;
			do {
				deptSubTitle();
				int pick = sc.nextInt();
				switch (pick) {
				case 1:
					deptSelectAll();
					break;
				case 2:
					deptSelect();
					break;
				case 3:
					deptInsert();
					break;
				case 4:
					deptUpdate();
					break;
				case 5:
					deptDelete();
					break;
				case 6:
					isTrue = true;
					System.out.println("> 이전 항목으로 돌아갑니다.");
					break;
				default:
					System.out.println("> 해당 항목이 존재하지 않습니다.");
					System.out.println("> 재입력해주세요.");
					break;
				}
			} while(!isTrue);
		}
		
		private void deptSelectAll() {
			List<DeptVO> list5 = new ArrayList<DeptVO>();
			list5 = deptS.deptSelectAll();
			System.out.println("===== 전 체 교 수 =====");
			for (DeptVO dvo : list5) {
				dvo.toString();
			}
			System.out.println("=====================");
			deptS.countDept(dvo);
		}

		private void deptSelect() {
			System.out.println("> 학과 조회를 시작합니다.");
			System.out.println("> 학과 이름을 입력해주세요.");
			dvo.setDeptName(sc.next());
			dvo = deptS.deptSelect(dvo);
			if(deptS.selectInit(dvo) == 1) {
				System.out.println("=======   학   과   =======");
				dvo.toString();
				System.out.println("=========================\n");
			} else {
				System.out.println("> 해당 학과가 존재하지 않습니다.");
			}
		}

		private void deptInsert() {
			int n;
			System.out.println("> 학과 등록을 시작합니다.");
			System.out.println("> 학과 코드를 입력해주세요.");
			dvo.setDeptCode(sc.next());
			System.out.println("> 학과 이름을 입력해주세요.");
			dvo.setDeptName(sc.next());
			n = deptS.deptInsert(dvo);
			if(n != 0) {
				System.out.println("> 학과 등록 완료");
			} else {
				System.out.println("> 학과 등록 실패");
			}
		}

		private void deptUpdate() {
			int n;
			System.out.println("> 변경할 학과 이름를 입력하세요.");
			dvo.setDeptName(sc.next());
			System.out.println("> 해당 학과코드를 입력하세요.");
			dvo.setDeptCode(sc.next());
			n = deptS.deptUpdate(dvo);
			if ( n != 0 ) {
				System.out.println("> 학과 수정 완료");
			} else {
				System.out.println("> 학과 수정 실패");
			}
		}

		private void deptDelete() {
			int n;
			System.out.println("> 삭제할 학과 코드를 입력해주세요.");
			dvo.setDeptCode(sc.next());
			n = deptS.deptDelete(dvo);
			if ( n!=0 ) {
				System.out.println("> 학과 삭제 완료");
			} else {
				System.out.println("> 학과 삭제 실패");
			}
		}
	}


