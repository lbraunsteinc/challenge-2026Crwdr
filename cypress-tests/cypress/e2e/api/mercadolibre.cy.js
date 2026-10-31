const api = require('../../services/ApiClient');

describe('API - Mercado Libre', () => {
  it('TC-API-01: GET /menu/departments devuelve departamentos', () => {
    // Consulto el servicio de departamentos de Mercado Libre Argentina
    api.get('/menu/departments').then((res) => {
      // La respuesta tiene que ser exitosa y venir en formato JSON
      expect(res.status).to.eq(200);
      expect(res.headers['content-type']).to.include('application/json');
      // El cuerpo debe traer la lista de departamentos y no estar vacía
      expect(res.body).to.have.property('departments');
      expect(res.body.departments).to.be.an('array').and.not.be.empty;
      // Reviso que cada departamento tenga nombre y su lista de categorías
      res.body.departments.forEach((d) => {
        expect(d).to.have.property('name').that.is.a('string').and.not.empty;
        expect(d.categories).to.be.an('array');
      });
    });
  });
});
