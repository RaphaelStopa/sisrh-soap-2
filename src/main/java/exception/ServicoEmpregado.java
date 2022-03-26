package exception;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.WebServiceContext;

import seguranca.Autenticador;
import sisrh.dto.Empregado;
import sisrh.dto.Empregados;


@WebService
@SOAPBinding(style = Style.RPC)
public class ServicoEmpregado {
	@Resource
	WebServiceContext context;

	
	//https://learn-us-east-1-prod-fleet02-xythos.content.blackboardcdn.com/5df7dfcfaf23d/979547?X-Blackboard-Expiration=1648350000000&X-Blackboard-Signature=YQ%2FfWaHW5%2Fs8KMx5l%2FCbfBYsmvL1Y0pLQn3xU8TSVTc%3D&X-Blackboard-Client-Id=528696&response-cache-control=private%2C%20max-age%3D21600&response-content-disposition=inline%3B%20filename%2A%3DUTF-8%27%27AtividadeFormativa_Semana3.pdf&response-content-type=application%2Fpdf&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEA4aCXVzLWVhc3QtMSJIMEYCIQCGL%2BAkyzGKcUkOTyF7bNMD8hh6wKzXJdegMoI3PCKlngIhAKtKl9qAZkXAZp2HKFqh4pLoUw8ieNlrf89OYnjr4HISKoMECJf%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEQAhoMNjM1NTY3OTI0MTgzIgzb9Qe4xJcvyYDB0%2Bwq1wOxtzULyhWZcJFwnqFXv3RQH%2FP0jSdz3fjB2kVva9v9eYqtwTntwEGx7S%2Ffww6I7KZ%2FBNs4pNq4Z%2FfsuMaS%2B6ON5Wlo4L9UAdjq7%2BY1tSwQCaubdmyRzglVB8KBBxAS%2FHpT3K2ezNxGlUJv%2BweXESmehplxN6MrWiCJrbpMyPapfQI8OTJfBQIg%2B%2BOKSNy80ATt6UijKl8eN%2FfcpVNmFp2L%2B%2BxnEXEO4zEl8SNi4cmDdgeU6LAD%2F6oauAa4cY4k7wXiwTc0sZovMDro5VyBfiEjGJL3ArfckqJuS7kNLW%2BwIeUmXqB1q7NMUs%2Bc0HDO8uzDOix4%2Bi%2F9QjGBbXLs%2FnIHEbTKq7Fy00oHrm3NNQV0Y7qTnT5CDjmwz1xqQBPtzp%2BIZKwMqEZpQkAwtkuu%2FoBoJ%2FRgbSw%2FAcqYzujVQ8oaO8a0RaK3KrLIVLA%2FOeCpTUtROSDg38jUdYs9TTEi2NnG7NWEregKb1jc1KQ5dHfDy%2B5C9PQwotMfW3hqccSORSmIActeXTQ5OQ3zhxsMVwSIDZIRlz3MNRKXc0mILTX9HGvyxh%2F2%2FLlW%2Bor7nJGKteWb9RnN9BnMg4uvlcZN028X%2FFA%2FhJFnAQLA0NA7lxDTyg0HW2J%2Bn0Ew15T%2BkQY6pAHwAFiMhx6e2LoTcU4IQo7ZCcGqt56Tx8NJOraib0%2F5JQ1Y%2FVTO0zuQRFBdQYJ2OwQdr%2Bn7tda57LZ%2FIrFpD50lop9FrBILvRIKODh%2FNpavtJt2NvkyCgzFw%2F4N6IVugWOELbtpgJBVzS%2Bz1WKyC9IU6eHQQJNdyCMVhJTXVi3UK3gxMdi6hk4rMu50U3nm95ME2OkPEcmiOJ40jNvC%2F6%2F8kZBImw%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20220326T210000Z&X-Amz-SignedHeaders=host&X-Amz-Expires=21600&X-Amz-Credential=ASIAZH6WM4PL7TS75XXG%2F20220326%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=9322b60fc65eedc901c647640f4f8e2469c001dcf6203a7ce0a19b479606a2ce
	// to na parte 14 e isto
	// alem de testar
	
	@WebMethod(action = "listar")
	public Empregados listar() throws Exception {
		Autenticador.autenticarUsuarioSenha(context);

		Empregados empregados = new Empregados();
		List<Empregado> lista = Banco.listarEmpregados();
		for(Empregado emp: lista) {
		empregados.getEmpregados().add(emp);
		}
		return empregados;
		}
}