/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unipiloto.ws.actividad1.services;

import co.edu.unipiloto.ws.actividad1.entidad.persona;
import co.edu.unipiloto.ws.actividad1.entidad.promedio;
import co.edu.unipiloto.ws.actividad1.entidad.suma;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
/**
 *
 * @author cristian-patino
 */

@Path("service")
public class servicio {
    
    private static Map<Integer, persona> personas = new HashMap<Integer, persona>();
    static{
        for (int i = 0; i < 10; i++) {
            persona people = new persona();
            int id = i+1;
            people.setId(id); 
            people.setNombre("Maravillosa persona " + id);            
            people.setEdad((int)(Math.random() * 60 + 1));
            people.setSalario(people.getEdad()*1000000/3);
            personas.put(id, people);
          
        }
    }
    
    
    @GET
    @Path("/getPersonaByIdXML/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public persona getPersonaByIdXML(@PathParam("id") int id){
        return personas.get(id);
    }
    
    @GET
    @Path("/getPersonaByIdJson/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public persona getPersonaByIdJson(@PathParam("id") int id){
        return personas.get(id);
    }
    
    @GET
    @Path("/getAllPersonaInXML")
    @Produces(MediaType.APPLICATION_XML)
    public List<persona> getAllPersonaInXML(){
        return new ArrayList<persona>(personas.values());
    }
    
    @GET
    @Path("/getAllPersonaInJson")
    @Produces(MediaType.APPLICATION_JSON)
    public List<persona> getAllPersonaInJson(){
        return new ArrayList<persona>(personas.values());
    }
    
    @GET
    @Path("/getPromedioSalarioXML")
    @Produces(MediaType.APPLICATION_XML)
    public promedio getPromedioSalarioXML(){
        int promedio = 0;
        for (persona i : new ArrayList<persona>(personas.values())) {
            promedio = promedio + i.getSalario();
        }
        promedio = promedio/personas.size();
        promedio p = new promedio();
        p.setProm(promedio);
        return p;
    }
    
    @GET
    @Path("/getSumarSalarioJson")
    @Produces(MediaType.APPLICATION_JSON)
    public suma getSumarSalarioJson(){
        int suma = 0;
        for (persona i : new ArrayList<persona>(personas.values())) {
            suma = suma + i.getSalario();
        }
        suma s = new suma();
        s.setSum(suma);
        return s;
    }
    
    @POST
    @Path("/addPersonaInJson")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public persona addPersonaInJson(persona person){
        System.out.println(person.getId());
        personas.put(new Integer(person.getId()), person);
        return person;
    }
}

