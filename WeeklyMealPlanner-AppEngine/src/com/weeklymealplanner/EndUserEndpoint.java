package com.weeklymealplanner;

import com.weeklymealplanner.EMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "enduserendpoint", namespace = @ApiNamespace(ownerDomain = "weeklymealplanner.com", ownerName = "weeklymealplanner.com", packagePath = ""))
public class EndUserEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listEndUser")
	public CollectionResponse<EndUser> listEndUser(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<EndUser> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from EndUser as EndUser");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<EndUser>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (EndUser obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<EndUser> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getEndUser")
	public EndUser getEndUser(@Named("id") String id) {
		EntityManager mgr = getEntityManager();
		EndUser enduser = null;
		try {
			enduser = mgr.find(EndUser.class, id);
		} finally {
			mgr.close();
		}
		return enduser;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param enduser the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertEndUser")
	public EndUser insertEndUser(EndUser enduser) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsEndUser(enduser)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(enduser);
		} finally {
			mgr.close();
		}
		return enduser;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param enduser the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateEndUser")
	public EndUser updateEndUser(EndUser enduser) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsEndUser(enduser)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(enduser);
		} finally {
			mgr.close();
		}
		return enduser;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeEndUser")
	public void removeEndUser(@Named("id") String id) {
		EntityManager mgr = getEntityManager();
		try {
			EndUser enduser = mgr.find(EndUser.class, id);
			mgr.remove(enduser);
		} finally {
			mgr.close();
		}
	}

	private boolean containsEndUser(EndUser enduser) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			EndUser item = mgr.find(EndUser.class, enduser.getEndUserID());
			if (item == null) {
				contains = false;
			}
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
