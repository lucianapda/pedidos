import {Produto} from "./Produto";

export class PedidoProduto {
    
      constructor(public produto:Produto, public id:number, public quantidade:number) {
      }
    }