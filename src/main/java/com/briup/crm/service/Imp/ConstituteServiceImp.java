package com.briup.crm.service.Imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.Contribution;
import com.briup.crm.service.ConstituteService;
import com.briup.crm.service.CustomerService;

@Service
public class ConstituteServiceImp implements ConstituteService {

	@Autowired
	private CustomerService customerService;
	
	@Override
	public List<Contribution> findCustMarkUp(int condition) {
		//所有顾客的人数
		int count = customerService.findAllCustomer().size();
		List<Contribution> conlist = new ArrayList<Contribution>();
		if(condition==0) {//等级
			Set<String> levels = customerService.findAllLevel();
			for(String level:levels) {
				//获得不同等级的顾客熟
				int size = customerService.getCustByLevel(level);
				//获得每个等级所占百分比
				float percent = (float)size/count;
				Contribution con = new Contribution();
				con.setName(level);
				con.setY(percent);
				conlist.add(con);
			}
		}else if(condition==1) {//信誉度
			Set<Integer> credits = customerService.findAllCreidt();
			for(Integer credit:credits) {
				//获得不同等级的顾客熟
				int size = customerService.getCustByCreidt(credit);
				//获得每个等级所占百分比
				float percent = (float)size/count;
				Contribution con = new Contribution();
				con.setName(""+credit);
				con.setY(percent);
				conlist.add(con);
			}
		}else if(condition==2) {//满意度
			Set<Integer> satisfies = customerService.findAllSatisfy();
			for(Integer satisfy:satisfies) {
				//获得不同等级的顾客熟
				int size = customerService.getCustBySatisfy(satisfy);
				//获得每个等级所占百分比
				float percent = (float)size/count;
				Contribution con = new Contribution();
				con.setName(""+satisfy);
				con.setY(percent);
				conlist.add(con);
			}
		}
		return conlist;
	}

}
