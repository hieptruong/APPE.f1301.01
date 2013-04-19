package ch.hslu.appe.fs1301.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.hslu.appe.fs1301.business.shared.AccessDeniedException;
import ch.hslu.appe.fs1301.business.shared.UserRole;
import ch.hslu.appe.fs1301.business.shared.iOrderAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellposition;
import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1301.business.shared.dto.DTOConverter;
import ch.hslu.appe.fs1301.data.shared.iOrderPositionRepository;
import ch.hslu.appe.fs1301.data.shared.iOrderRepository;
import ch.hslu.appe.fs1301.data.shared.iPersonRepository;
import ch.hslu.appe.fs1301.data.shared.iTransaction;
import ch.hslu.appe.fs1301.data.shared.entity.Bestellposition;
import ch.hslu.appe.fs1301.data.shared.entity.Bestellung;

import com.google.inject.Inject;

public class OrderAPI extends BaseAPI implements iOrderAPI {

	private iPersonRepository fPersonRepository;
	private iOrderRepository fOrderRepository;
	private iOrderPositionRepository fPositionRepository;

	@Inject
	public OrderAPI(iTransaction transaction, iInternalSessionAPI sessionAPI, iOrderRepository orderRepository, 
					iOrderPositionRepository positionRepository, iPersonRepository personRepository) {
		super(transaction, sessionAPI);
		fOrderRepository = orderRepository;
		fPositionRepository = positionRepository;
		fPersonRepository = personRepository;
	}

	@Override
	public DTOBestellung createNewOrder(int customerId, int source, List<DTOBestellposition> positions) throws AccessDeniedException {
		checkRole(UserRole.SYSUSER);
				
		fTransaction.beginTransaction();
		try {
			//Save Order
			Bestellung order = new Bestellung();
			order.setQuelle(source);
			order.setPerson1(fPersonRepository.getById(customerId));
			order.setPerson2(fSessionAPI.getAuthenticatedUser());
			order.setBestelldatum(new Date());
			fOrderRepository.persistObject(order);
			
			//Save positions
			if (orderProducts(order.getId(), positions) == false) {
				fTransaction.rollbackTransaction();
				return null;
			}
					
			DTOBestellung dtoBestellung = new DTOBestellung(fOrderRepository.getById(order.getId()));
			fTransaction.commitTransaction();
			return dtoBestellung;
		} catch (Exception exception) {
			fTransaction.rollbackTransaction();
			return null;
		}
	}
	
	private boolean orderProducts(int orderId, List<DTOBestellposition> positions) {
		for(DTOBestellposition position : positions) {
			if (!fPositionRepository.orderProduct(orderId, position.getProdukt(), position.getAnzahl(), position.getStueckpreis())) {
				//Not enough in storage
				return false;
			}
		}
		return true;
	}

	@Override
	public List<DTOBestellung> getOrders(int... ids) throws AccessDeniedException {
		checkRole(UserRole.SYSUSER);
		
		List<Bestellung> searchList;
		try {
			searchList = fOrderRepository.getOrdersByPersonIDs(ids);
		} catch(IllegalArgumentException exception) {
			searchList = new ArrayList<Bestellung>(0);
		}
		
		return DTOConverter.convertBestellung(searchList);
	}

	@Override
	public List<DTOBestellposition> getOrderPositions(int... ids) throws AccessDeniedException {
		checkRole(UserRole.SYSUSER);
		
		List<Bestellposition> searchList;
		try {
			searchList = fPositionRepository.getOrderPositionsById(ids);
		} catch(IllegalArgumentException exception) {
			searchList = new ArrayList<Bestellposition>(0);
		}
		
		return DTOConverter.convertBestellposition(searchList);
	}
}
