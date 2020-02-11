# DesafioHotelBackend

Hospede

Select
Get
http://localhost:8080/ProjetoHotel/hospede

SelectFilter
Get
http://localhost:8080/ProjetoHotel/hospede/busca?valorBusca={'String'}

Insert
Post
http://localhost:8080/ProjetoHotel/hospede

Delete
@DeleteMapping("/hospede/{id}")
http://localhost:8080/ProjetoHotel/hospede

Update
@PutMapping("/hospede/{id}")
http://localhost:8080/ProjetoHotel/hospede



Checkins

Get
@GetMapping("/checkin")

Insert
Post
http://localhost:8080/ProjetoHotel/checkin/post

get
hospedePresentes
http://localhost:8080/ProjetoHotel/checkin/open

get
hospedesNaoPresentes
http://localhost:8080/ProjetoHotel/checkin/closed


