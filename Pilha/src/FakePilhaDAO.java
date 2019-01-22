
public class FakePilhaDAO implements IPilhaDAO {

	@Override
	public void save(Pilha p) {
		System.out.println("Não falo com banco de dados");
	}

}
