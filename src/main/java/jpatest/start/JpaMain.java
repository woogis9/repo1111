package jpatest.start;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			// searchList(em);
			// remove(em);
			updateLogic(em);
			// logic(em);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

	/*
	 * Resist
	 */
	public static void logic(EntityManager em) {
		String name = "이종혁";
		Member member = new Member();
		member.setName(name);
		member.setMobile("01030253232");
		member.setPosition("대표");
		em.persist(member);
	};

	/*
	 * Update
	 */
	public static void updateLogic(EntityManager em) {

		// First Method!!
		// long seq = 2;
		// Member findMember = em.find(Member.class, seq);
		// findMember.setMobile("01030250010");

		// Second Method!!
		String str = "박용욱";
		List<Member> members = em.createQuery("select m from Member m where m.name = '" + str + "'", Member.class).getResultList();
		long seq = members.get(0).getSeq();
		Member findMember = em.find(Member.class, seq);
		findMember.setMobile("01099991112");
	};

	/*
	 * Search One
	 */
	public static void searchOne(EntityManager em) {
		String name = "박용욱";
		Member findMember = em.find(Member.class, name);
		System.out.println("조회결과 : " + findMember.getName());
	};

	/*
	 * Search List
	 */
	public static void searchList(EntityManager em) {
		List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
		int i = 1;
		for (Member mem : members) {
			System.out.println("사원[" + i + "] : " + mem.getName());
			++i;
		}
	};

	/*
	 * Remove
	 */
	public static void remove(EntityManager em) {
		String name = "박용욱";
		Member findMember = em.find(Member.class, name);
		em.remove(findMember.getName());
	};

}
