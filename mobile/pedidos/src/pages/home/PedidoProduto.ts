import {Produto} from "./Produto";

export class PedidoProduto {
    
      constructor(public product:Produto, public id:number, public quantidade:number) {
      }
    }