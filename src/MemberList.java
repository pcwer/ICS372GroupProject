import java.util.Iterator;
import java.util.LinkedList;

public class MemberList {
	private LinkedList<Member> members;
	private static MemberList memberList;
	
	public static MemberList getInstance() {
		if (memberList == null) {
			memberList = new MemberList();
		}

		return memberList;
	}

	private MemberList() {
	}
	
	public boolean addMember(Member member) {
		return members.add(member);
	}
	
	public boolean removeMember(String memberId) {
		for (Member member: members) {
			if (member.getMemberId().equals(memberId)) {
				members.remove(member);
				return true;
			}
		}

		return false;
	}
	
	public Member findMember(String memberId) {
		for (Member member: members) {
			if (member.getMemberId().equals(memberId)) {
				return member;
			}
		}

		return null;
	}
	
	public Iterator getMembers() {
		return members.iterator();
	}
}

