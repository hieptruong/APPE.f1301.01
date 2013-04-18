package ch.hslu.appe.fs1301.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.hslu.appe.fs1301.business.shared.iOrderAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellposition;
import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1301.business.shared.dto.DTOConverter;
import ch.hslu.appe.fs1301.data.shared.iOrderPositionRepository;
import ch.hslu.appe.fs1301.data.shared.iOrderRepository;
import ch.hslu.appe.fs1301.data.shared.iPersonRepository;
import ch.hslu.appe.fs1301.data.shared.entity.Bestellposition;
import ch.hslu.appe.fs1301.data.shared.entity.Bestellung;

import com.google.inject.Inject;

public class OrderAPI implements iOrderAPI {

	private iInternalSessionAPI fSessionAPI;
	private iPersonRepository fPersonRepository;
	private iOrderRepository fOrderRepository;
	private iOrderPositionRepository fPositionRepository;

	@Inject
	public OrderAPI(iInternalSessionAPI sessionAPI, iOrderRepository orderRepository, iOrderPositionRepository positionRepository, iPersonRepository personRepository) {
		fSessionAPI = sessionAPI;
		fOrderRepository = orderRepository;
		fPositionRepository = positionRepository;
		fPersonRepository = personRepository;
	}

	@Override
	public boolean createNewOrder(int customerId, int source, List<DTOBestellposition> positions) {
		//checkStorage()
		
		Bestellung order = new Bestellung();
		order.setQuelle(source);
		order.setPerson1(fPersonRepository.getById(customerId));
		order.setPerson2(fSessionAPI.getAuthenticatedUser());
		order.setBestelldatum(new Date());
		//order.set
		
		return false;
	}

	@Override
	public List<DTOBestellung> getOrders(int... ids) {
		List<Bestellung> searchList;
		try {
			searchList = fOrderRepository.getOrdersByPersonIDs(ids);
		} catch(IllegalArgumentException exception) {
			searchList = new ArrayList<Bestellung>(0);
		}
		
		return DTOConverter.convertBestellung(searchList);
	}

	@Override
	public List<DTOBestellposition> getOrderPositions(int... ids) {
		List<Bestellposition> searchList;
		try {
			searchList = fPositionRepository.getOrderPositionsById(ids);
		} catch(IllegalArgumentException exception) {
			searchList = new ArrayList<Bestellposition>(0);
		}
		
		return DTOConverter.convertBestellposition(searchList);
	}
}
