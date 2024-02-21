package com.example.pcbuilder.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.pcbuilder.R;
import com.example.pcbuilder.activities.ShowAllActivity;
import com.example.pcbuilder.adapter.AllProductAdapter;
import com.example.pcbuilder.adapter.CategoryAdapter;
import com.example.pcbuilder.adapter.NewProductAdapter;
import com.example.pcbuilder.adapter.PopularAdapter;
import com.example.pcbuilder.model.AllProductModel;
import com.example.pcbuilder.model.CategoryModel;
import com.example.pcbuilder.model.NewProductModel;
import com.example.pcbuilder.model.PopularModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    TextView catShowAll,popularShowAll,newProductShowAll,allProductShowAll;
    RecyclerView catRecyclerView,newProductRecyclerView,popularRecyclerView,allProductRecyclerView;

    //CatRecyclerView
    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryModelList;

    //NewProductRecyclerView
    NewProductAdapter newProductAdapter;
    List<NewProductModel> newProductModelsList;

    // PopularRecyclerView
    PopularAdapter popularAdapter;
    List<PopularModel> popularModelList;

    // AllProductRecyclerView
    AllProductAdapter allProductAdapter;
    List<AllProductModel> allProductModelList;

    FirebaseFirestore db;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_home, container, false);

        catRecyclerView=root.findViewById(R.id.rec_category);
        newProductRecyclerView=root.findViewById(R.id.new_product_rec);
        popularRecyclerView=root.findViewById(R.id.popular_rec);
        allProductRecyclerView=root.findViewById(R.id.all_product_rec);

        catShowAll = root.findViewById(R.id.category_see_all);
        popularShowAll = root.findViewById(R.id.popular_see_all);
        newProductShowAll = root.findViewById(R.id.newProducts_see_all);
        allProductShowAll = root.findViewById(R.id.allProducts_see_all);

        catShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        popularShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        newProductShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        allProductShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        ImageSlider imageSlider =root.findViewById(R.id.image_slider);
        List<SlideModel> slideModels =new ArrayList<>();

        ImageSlider imageSlide =root.findViewById(R.id.image_slide);
        List<SlideModel> slideModel =new ArrayList<>();

        db = FirebaseFirestore.getInstance();

        slideModels.add(new SlideModel(R.drawable.banner1, ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.banner2, ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.banner3, ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.banner7, ScaleTypes.CENTER_INSIDE));

        imageSlider.setImageList(slideModels);

        slideModel.add(new SlideModel(R.drawable.banner4,"", ScaleTypes.CENTER_INSIDE));
        slideModel.add(new SlideModel(R.drawable.banner5,"", ScaleTypes.CENTER_INSIDE));
        slideModel.add(new SlideModel(R.drawable.banner6,"", ScaleTypes.CENTER_INSIDE));
        slideModel.add(new SlideModel(R.drawable.banner8,"", ScaleTypes.CENTER_INSIDE));

        imageSlide.setImageList(slideModel);

        catRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryModelList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getContext(),categoryModelList);
        catRecyclerView.setAdapter(categoryAdapter);

        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document:task.getResult()){
                                CategoryModel categoryModel=document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();

                            }
                        }
                    }
                });

        newProductRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        newProductModelsList = new ArrayList<>();
        newProductAdapter = new NewProductAdapter(getContext(),newProductModelsList);
        newProductRecyclerView.setAdapter(newProductAdapter);

        db.collection("NewProduct")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document:task.getResult()){
                                NewProductModel newProductModel=document.toObject(NewProductModel.class);
                                newProductModelsList.add(newProductModel);
                                newProductAdapter.notifyDataSetChanged();

                            }
                        }
                    }
                });

        popularRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularModelList = new ArrayList<>();
        popularAdapter = new PopularAdapter(getContext(),popularModelList);
        popularRecyclerView.setAdapter(popularAdapter);

        db.collection("PopularProduct")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document:task.getResult()){
                                PopularModel PopularModel=document.toObject(PopularModel.class);
                                popularModelList.add(PopularModel);
                                popularAdapter.notifyDataSetChanged();

                            }
                        }
                    }
                });


        allProductRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        allProductModelList = new ArrayList<>();
        allProductAdapter = new AllProductAdapter(getContext(),allProductModelList);
        allProductRecyclerView.setAdapter(allProductAdapter);

        db.collection("AllProduct")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document:task.getResult()) {
                                AllProductModel AllProductModel = document.toObject(AllProductModel.class);
                                allProductModelList.add(AllProductModel);
                                allProductAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });

        return root;
    }
}
