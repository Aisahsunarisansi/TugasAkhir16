package com.informatika.databarang.adapter
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.telecom.Call
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.informatika.databarang.service.UpdateDataActivity
import com.informatika.databarang.model.ResponseActionBarang
import com.informatika.databarang.network.koneksi
import okhttp3.Response
import ....

class ListContent(val ldata : List<Dataltem?>?, val context: context) :
    RecyclerView.Adapter<ListContent.ViewHolder>(){
    class ViewHolder(val view: View) : RecylerView. ViewHolder (view){
        val namaBarang = view.findViewByld<TextView>(R.id.tv_nama_barang)
        val jmlBarang = view.findViewByld<TextView> (R.id.tv_jumlah_barang)
        val editBarang = view.findViewByld<TextView> (R.id.tv_edit)
        val deleteBarang = view.findViewById<TextView>(R.id.tv_delete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.item_barang, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ldata!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val model = ldata?.get(position)
        holder.namaBarang.text = model?.namaBarang
        holder.jmlBarang.text =model?.jumlahBarang
        holder.editBarang.setOnClickListener {
            val i = Intent(context, UpdateDataActivity:: class.java)
            i.putExtra("IDBARANG", model?.id)
            i.putExtra("NAMABARANG", model?.namaBarang)
            i.putExtra("JMLBARANG", model?.jumlahBarang)
            context.starActivity(i)
        }
        holder.deleteBarang.setOnClickListener{
            AlertDialog.Builder(context)
                .setTitle("Delete" + model?.namaBarang)
                .setMessage("Apakah Anda Ingin Menghapus Data Ini?")
                .setPositiveButton("Ya", DialogInterface.OnClickListener{dialog, which ->

                  //  koneksi.service.deleteBarang(model?.id).enqueue(object : callback<ResponseActionBarang>{
                       // override fun onFailure(call<ResponseActionBarang>,Throwable){
                            Log.d("pesan1", t.localizedMessage)
                        }

                        //override fun onResponse(
                           // call: Call<ResponseActionBarang>,
                           // response: Response<ResponseActionBarang>
                        ){
                           // if(response.isSuccessful){
                                Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_LONG).show()
                                //notifyDataSetChanged()
                               // notifyItemRemoved(position)
                               // notifyItemChanged(position)
                               // notifyItemRangeRemoved(position, ldata!!.size)
                               // Log.d("bpesan", response.body().toString())
                            }
                        }
                    })
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                    dialog.cancel()
                } )
                .show()
                }
        }
    }
}

