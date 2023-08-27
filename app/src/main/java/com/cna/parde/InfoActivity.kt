package com.cna.parde

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.children
import com.cna.parde.databinding.ActivityInfoBinding
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar

class InfoActivity : AppCompatActivity() {
    companion object {
        const val TAG_USERNAME = "USERNAME"
        const val TAG_CHIP_NAMES = "CHIP_NAMES"
    }

    private lateinit var binding : ActivityInfoBinding
    private val selectedChipNames = mutableListOf<String>()
    private lateinit var userNames :String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnInfo.setOnClickListener {

            if(!binding.txtName.text.isNullOrEmpty()) {

                userNames = binding.txtName.text.toString()

                selectedChipNames.clear()
                binding.chipGroup.children.toList().filterIsInstance<Chip>()
                    .filter { it.isChecked }
                    .forEach { chip ->
                        val chipName = chip.text.toString()
                        selectedChipNames.add(chipName)
                    }
                selectedChipNames.joinToString(", ")
                if (selectedChipNames.size > 3){
                    Snackbar.make(binding.root,"شما تنها میتوانید حداکثر 3 تا ژانر انتخاب کنید",Snackbar.LENGTH_LONG)
                        .setBackgroundTint(resources.getColor(R.color.main_background))
                        .show()
                }else{
                    val intent = Intent(this,MainActivity::class.java)
                    intent.putExtra(TAG_USERNAME, userNames)
                    intent.putStringArrayListExtra(TAG_CHIP_NAMES,ArrayList(selectedChipNames))
                    startActivity(intent)
                }
            }else{
                Snackbar.make(binding.root,"لطفا نام خود را وارد کنید !",Snackbar.LENGTH_LONG)
                    .setBackgroundTint(resources.getColor(R.color.main_background))
                    .show()
            }
        }
    }
}