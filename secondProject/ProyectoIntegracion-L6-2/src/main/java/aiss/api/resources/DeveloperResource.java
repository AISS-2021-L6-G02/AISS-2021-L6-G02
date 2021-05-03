package aiss.api.resources;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.jboss.resteasy.spi.BadRequestException;

import aiss.model.Developer;
import aiss.model.repository.DeveloperRepository;
import aiss.model.repository.MapDeveloperRepository;

@Path("/developers")
public class DeveloperResource {
	private static DeveloperResource instance = null;
	DeveloperRepository repository;
	
	private DeveloperResource() {
		repository = MapDeveloperRepository.getInstance();
	}
	public static DeveloperResource getInstance() {
		if(instance==null) {
			instance = new DeveloperResource();
		}
		return instance;
	}
	@GET
	@Produces("application/json")
	public Collection<Developer> getAll(@QueryParam("order") String order, @QueryParam("name") String name, @QueryParam("country") String country, @QueryParam("year") Integer year){
		Stream<Developer> result = repository.getAllDevelopers().stream();
		if(name!=null) {
			result = result.filter(x->x.getName().toLowerCase().contains(name.toLowerCase()));
		}
		if(country!=null) {
			result = result.filter(x-> x.getCountry().toLowerCase().equals(country.toLowerCase()));
		}
		if(year!=null) {
			result = result.filter(x-> x.getYear()>=year);
		}
		if(order!=null) {
			Boolean noValido = false;
			switch(order) {
			default:
				noValido = true;
				break;
			case "name":
				result = result.sorted(Comparator.comparing(Developer::getName));
				break;
			case "-name":
				result = result.sorted(Comparator.comparing(Developer::getName).reversed());
				break;
			case "year":
				result = result.sorted(Comparator.comparing(Developer::getYear));
				break;
			case "-year":
				result = result.sorted(Comparator.comparing(Developer::getYear).reversed());
				break;
			case "country":
				result = result.sorted(Comparator.comparing(Developer::getCountry));
				break;
			case "-country":
				result = result.sorted(Comparator.comparing(Developer::getCountry).reversed());
				break;
			}
			if(noValido) {
				throw new BadRequestException("The format of the order parameter must be name, -name, year, -year, country, or -country");
			}
		}
		return result.collect(Collectors.toList());
	}
	
}