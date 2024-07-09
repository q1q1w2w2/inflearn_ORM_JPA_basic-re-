package jpabook;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabook.domain.Member;
import jpabook.domain.Role;
import jpabook.domain.Team;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("selftest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setName("member1");
            member1.setRole(Role.USER);
            member1.setTeam(teamB);
            em.persist(member1);

            Member member2 = new Member();
            member2.setName("member2");
            member2.setRole(Role.USER);
            member2.setTeam(teamA);
            em.persist(member2);

            em.flush();
            em.clear();

            System.out.println("=============");

            Member result = em.createQuery("select m from Member m where m.name = 'member1'", Member.class)
                    .getSingleResult();
            System.out.println("result = " + result.getName());



            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}