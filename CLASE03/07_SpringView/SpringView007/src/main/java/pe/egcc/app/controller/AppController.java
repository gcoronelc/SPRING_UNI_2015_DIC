package pe.egcc.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pe.egcc.app.model.ProductoBean;
import pe.egcc.app.service.ProductoService;

/**
 * 
 * @author Gustavo Coronel
 * @blog   gcoronelc.blogspot.com
 *
 */
@Controller
public class AppController {

  @Autowired
	protected ProductoService productoService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {
		List<ProductoBean> listaProductos = productoService.getListaProductos();
		session.setAttribute("listaProductos", listaProductos);
		model.addAttribute("listaProductos", listaProductos);
		return "listaProductos";
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public ModelAndView getExcel() {
		List<ProductoBean> listaProductos = productoService.getListaProductos();
		return new ModelAndView("listaProductosExcelView", "listaProductos", listaProductos);
	}

	@RequestMapping(value = "/exportExcel2", method = RequestMethod.GET)
  public ModelAndView getExcel2(HttpSession session) {
    List<ProductoBean> listaProductos;
    listaProductos = (List<ProductoBean>) session.getAttribute("listaProductos");
    return new ModelAndView("listaProductosExcelView2", "listaProductos", listaProductos);
  }
	
	@RequestMapping(value = "/exportExcel3", method = RequestMethod.GET,
	    produces="application/vnd.ms-excel")
	@ResponseBody
  public String getExcel3(HttpSession session) {
    List<ProductoBean> listaProductos;
    listaProductos = (List<ProductoBean>) session.getAttribute("listaProductos");
    String datos = "ID\tNOMBRE\tPRECIO\tSTOCK\tIMPORTE\n";
    int n = 1;
    for(ProductoBean bean: listaProductos){
      n++;
     datos += bean.getId() + "\t" + bean.getNombre() 
     + "\t" + bean.getPrecio() + "\t" + bean.getStock()
     + "\t" + "=C" + n + "*D" + n + "\n";
    }
    return datos;
  }
	
	
}